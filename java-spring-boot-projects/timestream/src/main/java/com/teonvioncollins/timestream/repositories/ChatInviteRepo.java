package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.ChatInvite;
import com.teonvioncollins.timestream.models.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatInviteRepo extends JpaRepository<ChatInvite, Long> {

    List<ChatInvite> findByToUserAndStatus(
                String toUser,
                InviteStatus status
        );

        boolean existsByFromUserAndToUserAndStatus(
                String fromUser,
                String toUser,
                InviteStatus status
        );
    }
