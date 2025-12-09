package com.teonvioncollins.ReadingRealm.services;

import com.teonvioncollins.ReadingRealm.models.BookModel;
import com.teonvioncollins.ReadingRealm.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookModel> findBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}