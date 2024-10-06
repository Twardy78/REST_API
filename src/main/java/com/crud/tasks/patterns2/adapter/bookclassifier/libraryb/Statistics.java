package com.crud.tasks.patterns2.adapter.bookclassifier.libraryb;

import java.util.*;

public class Statistics implements BookStatistic {

    @Override
    public int averagePublicationYear(Map<BookSiganture, Book2> books) {
        if (books.size() == 0) return 0;
        int sum = 0;
        for (Map.Entry<BookSiganture, Book2> entry : books.entrySet()) {
            sum += entry.getValue().getYearOfPublication();
        }
        return sum / books.size();
    }

    @Override
    public int madianPublicationYear(Map<BookSiganture, Book2> books) {
        if (books.size() == 0) return 0;
        int [] years = new int[books.size()];
        int n = 0;
        for (Map.Entry<BookSiganture, Book2> entry : books.entrySet()) {
            years[n] = entry.getValue().getYearOfPublication();
            n++;
        }
        Arrays.sort(years);
        return years[years.length / 2];
    }
}
