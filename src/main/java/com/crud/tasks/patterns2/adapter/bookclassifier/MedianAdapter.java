package com.crud.tasks.patterns2.adapter.bookclassifier;

import com.crud.tasks.patterns2.adapter.bookclassifier.librarya.Book1;
import com.crud.tasks.patterns2.adapter.bookclassifier.librarya.Classifier;

import java.util.*;

public class MedianAdapter extends MedianAdaptee implements Classifier {

    @Override
    public int publicationYearMedian(Set<Book1> bookSet) {
        if (bookSet.isEmpty()) return 0;
        int[] year = new int[bookSet.size()];
        int n = 0;
        for (Book1 book1 : bookSet) {
            year[n++] = book1.getPublicationYear();
        }
        Arrays.sort(year);
        return year[year.length / 2];
    }
}
