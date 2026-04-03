package com.teonvioncollins.LibraryManager.repos;

import com.teonvioncollins.LibraryManager.models.LibraryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryModel, Long> {
}
