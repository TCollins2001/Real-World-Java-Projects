const sendBtn = document.getElementById("send-btn");
const input = document.getElementById("messageInput");
const chatMessages = document.getElementById("messages");

const username = document.getElementById("usernameField")?.value;
const sessionId = Number(document.getElementById("sessionIdField")?.value);

const protocol = location.protocol === "https:" ? "wss" : "ws";

const socket = new WebSocket(
  `${protocol}://${location.host}/chat?sessionId=${sessionId}&username=${encodeURIComponent(username)}`
);

socket.onmessage = (event) => {
  console.log("Received:", event.data);
  const msgData = JSON.parse(event.data);

  if (msgData.sessionId != sessionId) return;

  const currentUser = document.getElementById("usernameField")?.value;
  const isOwnMessage = msgData.username === currentUser;

  const wrapper = document.createElement("div");
    wrapper.classList.add("chat-message");
    wrapper.classList.add(isOwnMessage ? "sent" : "received");

    const bubble = document.createElement("div");
    bubble.classList.add("bubble");
    bubble.style.background = getUserGradient(msgData.username);

    bubble.innerHTML = `
      <span class="username">${msgData.username}</span>
      <div class="text">${msgData.message}</div>
    `;

    wrapper.appendChild(bubble);
    chatMessages.appendChild(wrapper);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  };

const userColorMap = new Map();

const predefinedGradients = [
  "linear-gradient(135deg, #0b3d91, #001f3f)",
  "linear-gradient(135deg, #b55364, #ffb6b9)"
];

function getUserGradient(username) {
  if (userColorMap.has(username)) {
    return userColorMap.get(username);
  }

  let gradient;

  if (userColorMap.size < predefinedGradients.length) {
    gradient = predefinedGradients[userColorMap.size];
  } else {
    gradient = generateRandomGradient(username);
  }

  userColorMap.set(username, gradient);
  return gradient;
}

function generateRandomGradient(seed) {
  let hash = 0;
  for (let i = 0; i < seed.length; i++) {
    hash = seed.charCodeAt(i) + ((hash << 5) - hash);
  }

  const hue1 = Math.abs(hash) % 360;
  const hue2 = (hue1 + 40 + (hash % 60)) % 360;

  return `linear-gradient(135deg,
    hsl(${hue1}, 70%, 45%),
    hsl(${hue2}, 75%, 60%)
  )`;
}


function sendMessage() {
  const usernameField = document.querySelector("#usernameField");
  const username = usernameField ? usernameField.value : "Guest";

  if (input.value.trim() === "") return;

  const msgObj = {
    username: username,
    message: input.value,
    sessionId: sessionId
  };

  socket.send(JSON.stringify(msgObj));
  input.value = "";
}

sendBtn.addEventListener("click", sendMessage);

input.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && !e.shiftKey) {
    e.preventDefault();
    sendMessage();
  }
});
