package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<ChatSession, Long> {

    List<ChatSession> findByUserAOrUserB(String userA, String userB);

    Optional<ChatSession> findByUserAAndUserB(String userA, String userB);

}
