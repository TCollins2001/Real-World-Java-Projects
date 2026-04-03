package com.teonvioncollins.LibraryManager.services;

import com.teonvioncollins.LibraryManager.models.LibraryModel;
import com.teonvioncollins.LibraryManager.repos.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public void addBook(String title, String author) {
        libraryRepository.save(new LibraryModel(title, author));
    }

    public List<LibraryModel> getAllBooks() {
        return libraryRepository.findAll();
    }

    public Long getTotalBooks() {
        return libraryRepository.count();
    }

    public void deleteBook(Long id) {
        libraryRepository.deleteById(id);
    }

    public void borrowBook(Long id) {
        LibraryModel libraryModel = libraryRepository.findById(id).orElseThrow();
        libraryModel.setAvailable(false);
        libraryRepository.save(libraryModel);
    }

    public void returnBook(Long id) {
        LibraryModel libraryModel = libraryRepository.findById(id).orElseThrow();
        libraryModel.setAvailable(true);
        libraryRepository.save(libraryModel);
    }

    public Long getAvailableBooks() {
        return libraryRepository.findAll().stream().filter(LibraryModel::isAvailable).count();
    }

    public Long getBorrowedBooks() {
        return libraryRepository.findAll().stream().filter(libraryModel -> !libraryModel.isAvailable()).count();
    }
}
