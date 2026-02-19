package com.teonvioncollins.timestream.repositories;

import com.teonvioncollins.timestream.models.LoginHistory;
import com.teonvioncollins.timestream.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepo extends JpaRepository<LoginHistory, Long> {

    List<LoginHistory> findTop10ByUserOrderByLoginTimeDesc(User user);

    void deleteByUserId(Long userId);

    void deleteByUser(User user);
}
