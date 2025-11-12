const socket = new WebSocket("ws://localhost:8080/chat");
const sendBtn = document.getElementById("send-btn");
const input = document.getElementById("messageInput");
const chatMessages = document.getElementById("messages");

socket.onmessage = (event) => {
  console.log("Received:", event.data);
  const msgData = JSON.parse(event.data);

  const msg = document.createElement("p");

  const usernameField = document.querySelector("#usernameField");
  const currentUser = usernameField ? usernameField.value : null;
  const isOwnMessage = msgData.username === currentUser;

    if (isOwnMessage) {
      msg.style.background = "linear-gradient(135deg, #0b3d91, #001f3f)";
      msg.style.color = "white";
      msg.style.alignSelf = "flex-end";
    } else {
      msg.style.background = "linear-gradient(135deg, #b55364, #ffb6b9)";
      msg.style.color = "white";
      msg.style.alignSelf = "flex-start";
    }

  msg.innerHTML = `<small style="color:#ffffff;font-size:12px;">${msgData.username}</small><br>${msgData.message}`;
  chatMessages.appendChild(msg);
  chatMessages.scrollTop = chatMessages.scrollHeight;
};

sendBtn.addEventListener("click", () => {
  const usernameField = document.querySelector("#usernameField");
  const username = usernameField ? usernameField.value : "Guest";

  if (input.value.trim() !== "") {
    const msgObj = {
      username: username,
      message: input.value
    };
    console.log("Sending:", msgObj);
    socket.send(JSON.stringify(msgObj));
    input.value = "";
  }
});