package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<MessageModel, Long> {

    List<MessageModel> findByChatIdOrderByIdAsc(Long chatId);

    void deleteByUsername(String username);
    void deleteByChatIdIn(List<Long> chatIds);

}