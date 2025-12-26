package com.teonvioncollins.ReadingRealm.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String image;
    private String synopsis;
    private String characters;
    private String format;
    private int pages;
    private String language;
    private String pdfFile;

    @Column(name = "display_order")
    private Integer displayOrder;

    public BookModel() {}

    public BookModel(Long id, String title, String author, String genre, String image, String synopsis, String characters, String format, int pages, String language, Integer displayOrder, String pdfFile) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.synopsis = synopsis;
        this.characters = characters;
        this.format = format;
        this.pages = pages;
        this.language = language;
        this.displayOrder = displayOrder;
        this.pdfFile = pdfFile;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre() {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage() {
        this.image = image;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }
}
