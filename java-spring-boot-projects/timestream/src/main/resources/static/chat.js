window.CURRENT_CHAT_USERS = new Set();

const sendBtn = document.getElementById("send-btn");
const input = document.getElementById("messageInput");
const chatMessages = document.getElementById("messages");
const participantsList = document.getElementById("participantsList");
const chatPreviewList = document.getElementById("chatPreviewList");

const leaveModal = document.getElementById("leaveModal");
const openModalBtn = document.getElementById("leaveChatBtn");
const stayBtn = document.getElementById("stayBtn");
const confirmLeaveBtn = document.getElementById("leaveBtn");


const username = document.getElementById("usernameField")?.value;
let sessionId = Number(document.getElementById("sessionIdField")?.value);

const protocol = location.protocol === "https:" ? "wss" : "ws";

let socket;

function connectSocket() {
  if (socket) socket.close();

  socket = new WebSocket(
    `${protocol}://${location.host}/chat?sessionId=${sessionId}&username=${encodeURIComponent(username)}`
  );

  socket.onmessage = (event) => {
    const msgData = JSON.parse(event.data);
    if (msgData.chatId !== sessionId) return;
    renderMessage(msgData);
  };
}

connectSocket();
loadMessages();

function renderMessage(msgData) {
  const isOwnMessage = msgData.username === username;
  const isSystem = msgData.system === true;

  const wrapper = document.createElement("div");

  if (isSystem) {
      wrapper.className = "chat-message system";
      wrapper.innerHTML = `<div class="system-text">${msgData.message}</div>`;
    } else {
      wrapper.className = `chat-message ${isOwnMessage ? "sent" : "received"}`;
      const bubble = document.createElement("div");
      bubble.className = "bubble";
      bubble.style.background = getUserGradient(msgData.username);

      bubble.innerHTML = `
        <span class="username">${msgData.username}</span>
        <div class="text">${msgData.message}</div>
      `;
      wrapper.appendChild(bubble);
    }

  chatMessages.appendChild(wrapper);
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

const userColorMap = new Map();

const predefinedGradients = [
  "linear-gradient(135deg, #0b3d91, #001f3f)",
  "linear-gradient(135deg, #b55364, #ffb6b9)"
];

function getUserGradient(user) {
  if (userColorMap.has(user)) return userColorMap.get(user);

  const gradient =
    userColorMap.size < predefinedGradients.length
      ? predefinedGradients[userColorMap.size]
      : generateRandomGradient(user);

  userColorMap.set(user, gradient);
  return gradient;
}

function generateRandomGradient(seed) {
  let hash = 0;
  for (let i = 0; i < seed.length; i++) {
    hash = seed.charCodeAt(i) + ((hash << 5) - hash);
  }

  const h1 = Math.abs(hash) % 360;
  const h2 = (h1 + 60) % 360;

  return `linear-gradient(135deg,
    hsl(${h1}, 70%, 45%),
    hsl(${h2}, 75%, 60%)
  )`;
}

function sendMessage() {
  if (!input.value.trim()) return;

  socket.send(JSON.stringify({
    username,
    message: input.value,
    chatId: sessionId
  }));

  input.value = "";
}

sendBtn.addEventListener("click", sendMessage);

input.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && !e.shiftKey) {
    e.preventDefault();
    sendMessage();
  }
});


let lastParticipants = [];

async function loadParticipants() {
  try {
    const res = await fetch(`/chat-participants?sessionId=${sessionId}`);
    if (!res.ok) return;

    const users = await res.json();
    if (JSON.stringify(users) === JSON.stringify(lastParticipants)) return;
    lastParticipants = users;

    participantsList.innerHTML = "";

    window.CURRENT_CHAT_USERS.clear();

    users.forEach(u => {
      if (u.active) {
          window.CURRENT_CHAT_USERS.add(u.username);
        }

      const li = document.createElement("li");
      li.className = "participant";

      if (!u.active) {
        li.innerHTML = `<s>${u.username}</s> <span class="left-label">(left)</span>`;
        li.classList.add("left");
      } else {
        li.textContent =
          u.username === username ? `${u.username} (You)` : u.username;
      }

      participantsList.appendChild(li);
    });

  } catch (err) {
    console.warn("Participant refresh failed:", err);
  }
}

loadParticipants();
const participantInterval = setInterval(loadParticipants, 3000);


async function loadChatPreviews() {
  const res = await fetch("/chat-previews");
  if (!res.ok) return;

  const chats = await res.json();
  chatPreviewList.innerHTML = "";

  chats
  .filter(chat => chat.participants.length > 1)
  .forEach(chat => {
    const div = document.createElement("div");
    div.className = "chat-preview-item";

    const names = chat.participants
      .filter(p => p !== username)
      .join(", ");

    div.textContent = names || "Just you";

    if (chat.id === sessionId) {
      div.classList.add("active");
    }

    div.onclick = () => switchChat(chat.id, div);
    chatPreviewList.appendChild(div);
  });
}

loadChatPreviews();


async function loadMessages() {
  const res = await fetch(`/chat-messages?sessionId=${sessionId}`);
  if (!res.ok) return;

  const messages = await res.json();
  chatMessages.innerHTML = "";
  userColorMap.clear();

  messages.forEach(renderMessage);
}

function switchChat(newChatId, el) {
  if (newChatId === sessionId) return;

  sessionId = newChatId;
  document.getElementById("sessionIdField").value = newChatId;

  history.pushState({}, "", `/chatroom?sessionId=${newChatId}`);

  chatMessages.innerHTML = "";
  userColorMap.clear();

  document.querySelectorAll(".chat-preview-item")
    .forEach(p => p.classList.remove("active"));

  el.classList.add("active");

  loadMessages();
  connectSocket();
  loadParticipants();
}

const chatPreviewInterval = setInterval(loadChatPreviews, 4000);

window.addEventListener("beforeunload", () => {
  clearInterval(participantInterval);
  clearInterval(chatPreviewInterval);
  if (socket) socket.close();
});

document.getElementById("leaveChatBtn").onclick = async () => {
  if (!confirm("Leave this chat?")) return;

  await fetch(`/leave-chat?chatId=${sessionId}`, {
    method: "POST"
  });

  if (socket) socket.close();

  window.location.href = "/all-chats";
};

openModalBtn.onclick = () => {
    leaveModal.classList.remove("hidden");
};

stayBtn.onclick = () => {
    leaveModal.classList.add("hidden");
};

confirmLeaveBtn.onclick = async () => {
    try {
        await fetch(`/leave-chat?chatId=${sessionId}`, {
            method: "POST"
        });

        if (socket) socket.close();
        window.location.href = "/all-chats";
    } catch (err) {
        console.error("Failed to leave chat:", err);
        leaveModal.classList.add("hidden");
    }
};

leaveModal.onclick = (e) => {
    if (e.target === leaveModal) {
        leaveModal.classList.add("hidden");
    }
};

function openInviteModalForExistingChat() {
  window.EXISTING_CHAT_ID = sessionId;
  openInviteModal();
}

document.getElementById("addUserBtn").addEventListener("click", () => {
  openInviteModalForExistingChat();
});
