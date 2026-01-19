document.addEventListener("DOMContentLoaded", () => {
    const input = document.getElementById("userSearch");
    const resultsDiv = document.getElementById("searchResults");

    if (!input || !resultsDiv) return;

    document.addEventListener("click", (event) => {
        if (
            !input.contains(event.target) &&
            !resultsDiv.contains(event.target)
        ) {
            resultsDiv.classList.remove("visible");
            resultsDiv.innerHTML = "";
        }
    });

    input.addEventListener("input", async () => {
        const query = input.value.trim();
        resultsDiv.innerHTML = "";

        if (query.length < 2) {
            resultsDiv.classList.remove("visible");
            return;
        }

        const res = await fetch(`/search-users?q=${encodeURIComponent(query)}`);
        const users = await res.json();

        if (users.length === 0) {
            resultsDiv.classList.remove("visible");
            return;
        }

        users.forEach(username => {
            const div = document.createElement("div");
            div.classList.add("user-result");
            div.textContent = username;

            div.onclick = async () => {
                await fetch(
                    `/invite-chat?username=${encodeURIComponent(username)}`,
                    { method: "POST" }
                );

                resultsDiv.classList.remove("visible");
                resultsDiv.innerHTML = "";
                input.value = "";

                alert(`Invite sent to ${username}`);
            };

            resultsDiv.appendChild(div);
        });

        resultsDiv.classList.add("visible");
    });
});
