const searchInput = document.getElementById("userSearch");
const resultsBox = document.getElementById("searchResults");
const chipInput = document.getElementById("chipInput");
const inviteBtn = document.getElementById("inviteBtn");

const selectedUsers = new Set();

searchInput.addEventListener("input", async () => {
  const q = searchInput.value.trim();

  if (!q) {
    resultsBox.innerHTML = "";
    resultsBox.classList.remove("visible");
    return;
  }

  const res = await fetch(`/search-users?q=${q}`);
  const users = await res.json();

  resultsBox.innerHTML = "";

  users.forEach(u => {
    if (selectedUsers.has(u)) return;

    const div = document.createElement("div");
    div.textContent = u;
    div.classList.add("user-result");

    div.addEventListener("click", () => {
      addChip(u);
      searchInput.value = "";
      resultsBox.innerHTML = "";
      resultsBox.classList.remove("visible");
    });

    resultsBox.appendChild(div);
  });

  resultsBox.classList.add("visible");
});


function addChip(username) {
  if (selectedUsers.has(username)) return;
  selectedUsers.add(username);

  const chip = document.createElement("div");
  chip.className = "user-chip";
  chip.dataset.user = username;
  chip.innerHTML = `
    ${username}
    <button>&times;</button>
  `;

  chip.querySelector("button").addEventListener("click", () => {
    selectedUsers.delete(username);
    chip.remove();
  });

  chipInput.insertBefore(chip, searchInput);
}

inviteBtn.addEventListener("click", async () => {
  let chatId = document.getElementById("activeChatId").value;

  if (selectedUsers.size === 0) return;
  if (!chatId) {
    const res = await fetch("/create-chat", { method: "POST" });
    chatId = await res.json();
    document.getElementById("activeChatId").value = chatId;
  }

  for (const user of selectedUsers) {
    await fetch("/invite-chat", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `chatId=${chatId}&username=${encodeURIComponent(user)}`
    });
  }

  alert("Invite sent. Waiting for acceptance.");
  selectedUsers.clear();
  document.querySelectorAll(".user-chip").forEach(c => c.remove());
});


