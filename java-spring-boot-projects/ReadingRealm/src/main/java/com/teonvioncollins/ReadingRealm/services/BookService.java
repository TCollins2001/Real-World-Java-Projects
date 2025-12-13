package com.teonvioncollins.ReadingRealm.services;

import com.teonvioncollins.ReadingRealm.models.BookModel;
import com.teonvioncollins.ReadingRealm.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public BookModel getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}