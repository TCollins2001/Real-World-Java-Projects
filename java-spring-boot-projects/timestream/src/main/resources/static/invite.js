let inviteInterval;

function openInviteModal() {
  clearInterval(inviteInterval);
  loadInvites();
  document.getElementById("inviteModal")?.classList.remove("hidden");
}

function closeInviteModal() {
  document.getElementById("inviteModal")?.classList.add("hidden");
  inviteInterval = setInterval(loadInvites, 5000);
}

function updateInviteBadge(invites) {
  const badge = document.getElementById("inviteBadge");
  if (!badge) return;

  if (!invites || invites.length === 0) {
    badge.classList.add("hidden");
    return;
  }

  badge.textContent = invites.length > 9 ? "9+" : invites.length;
  badge.classList.remove("hidden");
}

async function loadInvites() {
  try {
    const res = await fetch("/pending-invites");
    const invites = await res.json();

    const inviteList = document.getElementById("inviteList");
    if (!inviteList) return;

    inviteList.innerHTML = "";

    updateInviteBadge(invites);

    if (invites.length === 0) {
      inviteList.innerHTML = "<p>No pending invites for now.</p>";
      return;
    }

    invites.forEach(invite => {
      const div = document.createElement("div");
      div.className = "invite-item";
      div.innerHTML = `
        <strong>${invite.fromUser}</strong> wants to chat
        <div class="invite-actions">
          <button onclick="acceptInvite(${invite.id})">Accept</button>
          <button onclick="declineInvite(${invite.id})">Decline</button>
        </div>
      `;
      inviteList.appendChild(div);
    });

  } catch (err) {
    console.error("Failed to load invites:", err);
  }
}

async function acceptInvite(id) {
  try {
    const res = await fetch(`/accept-invite/${id}`, { method: "POST" });
    const data = await res.json();
    loadInvites();

    const participants = data.participants || [];
    const displayNames = participants.length > 0 ? participants.join(" • ") : "New Chat";

    if (typeof addOrUpdateChatPreview === "function") {
        addOrUpdateChatPreview(data.chatId || data, displayNames);
    }

  } catch (err) {
    console.error("Failed to accept invite:", err);
  }
}



async function declineInvite(id) {
  try {
    await fetch(`/decline-invite/${id}`, { method: "POST" });
    loadInvites();
  } catch (err) {
    console.error("Failed to decline invite:", err);
  }
}

document.addEventListener("DOMContentLoaded", () => {
  loadInvites();
  inviteInterval = setInterval(loadInvites, 5000);

  const modal = document.getElementById("inviteModal");
  const content = document.querySelector(".invite-modal-content");
  const closeBtn = document.getElementById("closeInviteBtn");
  const envelope = document.getElementById("inviteEnvelope");

  if (envelope) {
    envelope.addEventListener("click", e => {
      e.stopPropagation();
      openInviteModal();
    });
  }

  if (modal) {
    modal.addEventListener("click", closeInviteModal);
  }

  if (content) {
    content.addEventListener("click", e => e.stopPropagation());
  }

  if (closeBtn) {
    closeBtn.addEventListener("click", e => {
      e.stopPropagation();
      closeInviteModal();
    });
  }
});
