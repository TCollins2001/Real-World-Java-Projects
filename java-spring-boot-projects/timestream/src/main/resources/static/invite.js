function openInviteModal() {
    loadInvites();
    document.getElementById("inviteModal").classList.remove("hidden");
}

function closeInviteModal() {
    document.getElementById("inviteModal").classList.add("hidden");
}

async function loadInvites() {
    const res = await fetch("/pending-invites");
    const invites = await res.json();

    const inviteList = document.getElementById("inviteList");
    const badge = document.getElementById("inviteBadge");

    if (!inviteList || !badge) return;

    inviteList.innerHTML = "";

    badge.classList.toggle("hidden", invites.length === 0);

    if (invites.length === 0) {
        inviteList.innerHTML = "<p>No pending invites</p>";
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
}

async function acceptInvite(id) {
    const res = await fetch(`/accept-invite/${id}`, { method: "POST" });
    const chatId = await res.json();
    window.location.href = `/chatroom?sessionId=${chatId}`;
}

async function declineInvite(id) {
    await fetch(`/decline-invite/${id}`, { method: "POST" });
    loadInvites();
}

document.addEventListener("DOMContentLoaded", () => {
    loadInvites();

    const modal = document.getElementById("inviteModal");
    const content = document.querySelector(".invite-modal-content");
    const closeBtn = document.getElementById("closeInviteBtn");
    const envelope = document.getElementById("inviteEnvelope");

    if (envelope) {
        envelope.addEventListener("click", (e) => {
            e.stopPropagation();
            openInviteModal();
        });
    }

    modal.addEventListener("click", closeInviteModal);

    content.addEventListener("click", (e) => {
        e.stopPropagation();
    });

    closeBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        closeInviteModal();
    });
});

setInterval(() => {
    loadInvites();
}, 5000);
