package com.bideafactory.lab.testhomerent.util;

import com.bideafactory.lab.testhomerent.message.schemas.request.internal.BookRequest;
import com.bideafactory.lab.testhomerent.model.entities.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) public class HomeRentUtil {

    /**
     * Método para generar {@code Book} a partir de un {@code BookRequest}
     *
     * @param bookRequest Datos del book
     * @return instancia generada de {@code Book}
     */
    public static Book transformBookFromBookRequest(BookRequest bookRequest) {
        Book book = new Book();
        book.setId(bookRequest.getId());
        book.setName(bookRequest.getName());
        book.setLastName(bookRequest.getLastName());
        book.setAge(bookRequest.getAge());
        book.setPhoneNumber(bookRequest.getPhoneNumber());
        book.setStartDate(bookRequest.getStartDate());
        book.setEndDate(bookRequest.getEndDate());
        book.setHouseId(bookRequest.getHouseId());
        book.setDiscountCode(bookRequest.getDiscountCode());
        return book;
    }
}
