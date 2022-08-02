package com.bideafactory.lab.testhomerent.model.daos;

import com.bideafactory.lab.testhomerent.model.entities.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookDao extends ReactiveCrudRepository<Book, Long> {
}
