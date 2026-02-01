package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.ChatParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepo extends JpaRepository<ChatParticipants, Long> {

    List<ChatParticipants> findByUsername(String username);

    boolean existsByChatIdAndUsername(Long chatId, String username);
}
