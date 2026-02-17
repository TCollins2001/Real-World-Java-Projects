package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<ChatSession, Long> {

    @Query("SELECT c FROM ChatSession c JOIN ChatParticipants p ON c.id = p.chatId " +
            "WHERE p.username = :username AND p.active = true")
    List<ChatSession> findChatsByUsername(@Param("username") String username);

    @Query("""
  select cp.username
  from ChatParticipants cp
  where cp.chatId = :chatId
""")
    List<String> findParticipants(Long chatId);


    void deleteByOwner(User owner);

    List<ChatSession> findByOwner(User user);
}
