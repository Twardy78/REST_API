package com.crud.tasks.patterns2.adapter.bookclassifier;

import com.crud.tasks.patterns2.adapter.bookclassifier.librarya.Book1;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianAdapterTestSuite {

    @Test
    public void publicationYearMedianTest() {
        //Given
        MedianAdapter medianAdapter = new MedianAdapter();
        Set<Book1> books = new HashSet<>();
        Book1 book1 = new Book1("Tim Braddy", "Dog House", 2010, "1/2010");
        Book1 book2 = new Book1("Ivone Bellok", "Lost moon", 2004, "1/2004");
        Book1 book3 = new Book1("Jenifer Brown", "Port of love", 2004, "2/2004");
        Book1 book4 = new Book1("Timmoty Viberto", "Crash", 2005, "1/2005");
        Book1 book5 = new Book1("Susan Toller", "Limbo", 2006, "1/2006");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        //When
        int newPublicationYearMedian = medianAdapter.publicationYearMedian(books);

        //Than
        assertEquals(2005,newPublicationYearMedian);
        System.out.println(newPublicationYearMedian);
    }
}
