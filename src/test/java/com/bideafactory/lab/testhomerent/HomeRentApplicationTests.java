package com.bideafactory.lab.testhomerent;

import com.bideafactory.lab.testhomerent.model.daos.BookDao;
import com.bideafactory.lab.testhomerent.model.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest class HomeRentApplicationTests {

    @Autowired private BookDao bookDao;

    @Test void testFindAll() {
        assertEquals(0, (long) bookDao.findAll().count().block());
    }

    @Test void testSave() {
        Book book = new Book(null, "14564088-4", "Name 1", "Last name 1", 24,
            "56988123222", "2022-07-31", "2022-07-31", "4874552", null);
        book = bookDao.save(book).block();
        assertNotNull(book.getConsecutive());
    }

}
