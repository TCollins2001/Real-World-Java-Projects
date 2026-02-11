let inviteInterval;

function openInviteModal() {
  console.log("OPEN fired");
  clearInterval(inviteInterval);
  loadInvites();
  document.getElementById("inviteModal")?.classList.remove("hidden");
}

function closeInviteModal() {
  console.log("CLOSE fired");
  document.getElementById("inviteModal")?.classList.add("hidden");
  window.EXISTING_CHAT_ID = null;

  const searchInput = document.getElementById("userSearch");
  if (searchInput) {
    searchInput.value = "";
  }
  document.querySelectorAll(".user-chip").forEach(c => c.remove());
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

    updateInviteBadge(invites);

    const inviteList = document.getElementById("inviteList");
    if (!inviteList) return;

    inviteList.innerHTML = "";

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
        addOrUpdateChatPreview(
          data.id,
          displayNames,
          data.customRoomName
        );
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

const inviteBtn = document.getElementById("inviteBtn");
const inviteConfirmBtn = document.getElementById("inviteConfirmBtn");
const envelope = document.getElementById("inviteEnvelope");
const badge = document.getElementById("inviteBadge");
  if (!envelope || !badge) return;

  loadInvites();
  inviteInterval = setInterval(loadInvites, 5000);

  const modal = document.getElementById("inviteModal");
  const inviteContent = document.querySelector(".invite-modal-content");
  const closeBtn = document.getElementById("closeInviteBtn");
  const okBtn = document.getElementById("okBtn");
  const inviteSentModal = document.getElementById("inviteSentModal");

  if (okBtn) {
    okBtn.addEventListener("click", () => {
      inviteSentModal.classList.add("hidden");
    });
  }

  if (inviteSentModal) {
    inviteSentModal.addEventListener("click", (e) => {
      if (e.target === inviteSentModal) {
        inviteSentModal.classList.add("hidden");
      }
    });
  }


  if (envelope) {
    envelope.addEventListener("click", (e) => {
      e.preventDefault();
      e.stopPropagation();
      openInviteModal();
    });
  }

if (modal) {
  modal.addEventListener("click", (e) => {
    if (e.target === modal) {
      closeInviteModal();
    }
  });
}

  if (inviteContent) {
      inviteContent.addEventListener("click", e => {
        e.stopPropagation();
      });
    }

  if (closeBtn) {
    closeBtn.addEventListener("click", (e) => {
      e.stopPropagation();
      closeInviteModal();
    });
  }


let lastCreatedChatId = null;

if (inviteBtn) {
inviteBtn.addEventListener("click", async () => {
  const companionChips = document.querySelectorAll(".user-chip");
  const companions = Array.from(companionChips).map(chip => {
    const temp = chip.cloneNode(true);
    temp.querySelector("button")?.remove();
    return temp.textContent.trim();
  });

  if (window.EXISTING_CHAT_ID && window.CURRENT_CHAT_USERS) {
    const invalid = companions.filter(u =>
      window.CURRENT_CHAT_USERS.has(u)
    );

    if (invalid.length > 0) {
      alert(`Already in chat: ${invalid.join(", ")}`);
      return;
    }
  }

  if (companions.length === 0) {
    alert("Please add at least one companion!");
    return;
  }

  const chatId = window.EXISTING_CHAT_ID || null;

  try {
  if (chatId) {
    for (const user of companions) {
      await fetch("/invite-chat", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `chatId=${chatId}&username=${encodeURIComponent(user)}`
      });
    }

    document.getElementById("inviteModal")?.classList.add("hidden");
    document.getElementById("inviteSentModal")?.classList.remove("hidden");
    } else {
      const response = await fetch("/send-invite", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ invitees: companions })
      });

      if (!response.ok) throw new Error("Invite failed");

      lastCreatedChatId = await response.json();
      document.getElementById("inviteSentModal").classList.remove("hidden");
    }

    const searchInput = document.getElementById("userSearch");
    if (searchInput) {
      searchInput.value = "";
    }
    companionChips.forEach(chip => chip.remove());

  } catch (err) {
    console.error(err);
    alert("Could not send invite");
  }
});
}

  if (inviteConfirmBtn) {
  inviteConfirmBtn.addEventListener("click", async () => {
    const name = document.getElementById("finalChatNameInput").value.trim();

    if (name && lastCreatedChatId) {
      await fetch("/rename-chat", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `chatId=${lastCreatedChatId}&name=${encodeURIComponent(name)}`
      });
    }

    document.getElementById("finalChatNameInput").value = "";
    document.getElementById("inviteSentModal").classList.add("hidden");
  });
  }
  });

