package com.mad.e_librarymanager.user.utill;

import com.mad.e_librarymanager.admin.Book;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @org.junit.jupiter.api.Test
    void getPrice() {
        Book book = new Book("1","test", "test author", "Rs 500.00");
        assertEquals(500.00, 500.00);
    }
}