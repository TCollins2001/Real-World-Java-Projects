package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.ChatParticipants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepo extends JpaRepository<ChatParticipants, Long> {

    List<ChatParticipants> findByUsername(String username);

    boolean existsByChatIdAndUsernameAndActiveTrue(Long chatId, String username);

    @Modifying
    @Query("""
    UPDATE ChatParticipants p
    SET p.active = false
    WHERE p.chatId = :chatId AND p.username = :username
    """)

    void markLeft(Long chatId, String username);

    List<ChatParticipants> findByChatId(Long chatId);

    @Transactional
    void deleteByChatIdAndUsername(Long chatId, String username);

    void deleteByUsername(String username);
}
