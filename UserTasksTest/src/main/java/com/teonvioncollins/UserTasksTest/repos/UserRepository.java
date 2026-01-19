package com.teonvioncollins.UserTasksTest.repos;

import com.teonvioncollins.UserTasksTest.models.TaskModel;
import com.teonvioncollins.UserTasksTest.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
