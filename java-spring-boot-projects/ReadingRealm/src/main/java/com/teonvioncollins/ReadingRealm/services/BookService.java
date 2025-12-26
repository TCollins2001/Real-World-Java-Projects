package com.teonvioncollins.ReadingRealm.services;

import com.teonvioncollins.ReadingRealm.models.BookModel;
import com.teonvioncollins.ReadingRealm.repo.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "displayOrder"));
    }

    public List<BookModel> findBooksByGenre(String genre, Sort sort) {
        return bookRepository.findByGenre(genre, Sort.by(Sort.Direction.ASC, "displayOrder"));
    }

    public BookModel getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<BookModel> searchBooks(String query) {
        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        query,
                        query,
                        Sort.by(Sort.Direction.ASC, "displayOrder")
                );
    }
}