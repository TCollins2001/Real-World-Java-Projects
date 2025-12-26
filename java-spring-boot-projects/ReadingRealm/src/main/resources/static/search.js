document.addEventListener("DOMContentLoaded", () => {
    const input = document.getElementById("searchInput");
    const resultsDiv = document.getElementById("results-wrapper");

    let timeout = null;

    input.addEventListener("input", () => {
        clearTimeout(timeout);

        timeout = setTimeout(() => {
            const query = input.value.trim();

            if (query === "") {
                fetch("/get-books-fragment")
                    .then(res => res.text())
                    .then(html => resultsDiv.innerHTML = html);
                return;
            }

            fetch(`/search-live?q=${encodeURIComponent(query)}`)
                .then(res => res.text())
                .then(html => resultsDiv.innerHTML = html);

        }, 300);
    });
});
