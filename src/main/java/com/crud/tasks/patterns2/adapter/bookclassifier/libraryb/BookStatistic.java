package com.crud.tasks.patterns2.adapter.bookclassifier.libraryb;

import java.util.Map;

public interface BookStatistic {
    int averagePublicationYear(Map<BookSiganture, Book2> books);
    int madianPublicationYear(Map<BookSiganture, Book2> books);

}
