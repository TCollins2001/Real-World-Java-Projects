const socket = new WebSocket("ws://localhost:8080/chat");
const sendBtn = document.getElementById("send-btn");
const input = document.getElementById("messageInput");
const chatMessages = document.getElementById("messages");

socket.onmessage = (event) => {
  const msg = document.createElement("p");
  msg.textContent = event.data;
  chatMessages.appendChild(msg);
  chatMessages.scrollTop = chatMessages.scrollHeight;
};

sendBtn.addEventListener("click", () => {
  if (input.value.trim() !== "") {
    socket.send(input.value);
    input.value = "";
  }
});
