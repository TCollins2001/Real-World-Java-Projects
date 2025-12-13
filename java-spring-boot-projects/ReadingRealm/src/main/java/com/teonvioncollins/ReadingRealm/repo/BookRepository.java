package com.teonvioncollins.ReadingRealm.repo;

import com.teonvioncollins.ReadingRealm.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    List<BookModel> findByGenre(String genre);
}
