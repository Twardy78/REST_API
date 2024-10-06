package com.crud.tasks.patterns2.adapter.bookclassifier.libraryb;

public class Book2 {
    private final String author;
    private final String title;
    private final int yearOfPublication;

    public Book2(final String author, final String title, final int yearOfPublication) {
        this.author = author;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
    }
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public int getYearOfPublication() {
        return yearOfPublication;
    }
}
