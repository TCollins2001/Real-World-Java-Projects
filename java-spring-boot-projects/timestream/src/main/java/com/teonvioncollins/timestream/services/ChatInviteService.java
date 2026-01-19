package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.ChatInvite;
import com.teonvioncollins.timestream.models.InviteStatus;
import com.teonvioncollins.timestream.repositories.ChatInviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatInviteService {

    @Autowired
    private ChatInviteRepo chatInviteRepo;

    public void sendInvite(String fromUser, String toUser) {

        if (fromUser.equalsIgnoreCase(toUser)) {
            throw new IllegalArgumentException("You cannot invite yourself");
        }

        boolean alreadyInvited =
                chatInviteRepo.existsByFromUserAndToUserAndStatus(
                        fromUser,
                        toUser,
                        InviteStatus.PENDING
                );

        if (alreadyInvited) {
            return;
        }

        ChatInvite chatInvite = new ChatInvite();
        chatInvite.setFromUser(fromUser);
        chatInvite.setToUser(toUser);
        chatInvite.setStatus(InviteStatus.PENDING);

        chatInviteRepo.save(chatInvite);
    }

    public List<ChatInvite> getPendingInvites(String username) {
        return chatInviteRepo.findByToUserAndStatus(
                username,
                InviteStatus.PENDING
        );
    }

    public ChatInvite acceptInvite(Long inviteId) {

        ChatInvite chatInvite = chatInviteRepo.findById(inviteId)
                .orElseThrow(() -> new RuntimeException("Invite Not Found"));

        chatInvite.setStatus(InviteStatus.ACCEPTED);
            return chatInviteRepo.save(chatInvite);
        }

        public void declineInvite(Long inviteId) {
        chatInviteRepo.deleteById(inviteId);
    }
}
