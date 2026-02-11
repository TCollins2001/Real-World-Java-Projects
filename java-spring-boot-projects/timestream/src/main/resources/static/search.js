document.addEventListener("DOMContentLoaded", () => {
const searchInput = document.getElementById("userSearch");
const resultsBox = document.getElementById("searchResults");
const chipInput = document.getElementById("chipInput");
const inviteBtn = document.getElementById("inviteBtn");

if (!searchInput || !resultsBox || !chipInput) {
    return;
  }

const currentUser = window.CURRENT_USER;

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

   if (u === currentUser) return;
   if (selectedUsers.has(u)) return;

   if (
     window.EXISTING_CHAT_ID &&
     window.CURRENT_CHAT_USERS &&
     window.CURRENT_CHAT_USERS.has(u)
   ) {
     return;
   }

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
  if (username === currentUser) return;
  if (selectedUsers.has(username)) return;

  if (
    window.EXISTING_CHAT_ID &&
    window.CURRENT_CHAT_USERS &&
    window.CURRENT_CHAT_USERS.has(username)
  ) {
    alert(`${username} is already in this chat.`);
    return;
  }
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
});

