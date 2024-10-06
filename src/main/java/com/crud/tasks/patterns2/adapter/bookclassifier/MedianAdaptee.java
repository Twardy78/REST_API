package com.crud.tasks.patterns2.adapter.bookclassifier;

import com.crud.tasks.patterns2.adapter.bookclassifier.libraryb.Book2;
import com.crud.tasks.patterns2.adapter.bookclassifier.libraryb.BookSiganture;
import com.crud.tasks.patterns2.adapter.bookclassifier.libraryb.BookStatistic;
import com.crud.tasks.patterns2.adapter.bookclassifier.libraryb.Statistics;

import java.util.Map;

public class MedianAdaptee implements BookStatistic {

    @Override
    public int averagePublicationYear(Map<BookSiganture, Book2> books) {
        Statistics statistics = new Statistics();
        return statistics.averagePublicationYear(books);
    }

    @Override
    public int madianPublicationYear(Map<BookSiganture, Book2> books) {
        Statistics statistics = new Statistics();
        return statistics.madianPublicationYear(books);

    }

}
