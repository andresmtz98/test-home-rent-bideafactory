package com.bideafactory.lab.testhomerent.service;

import com.bideafactory.lab.testhomerent.adapters.DiscountApiAdapter;
import com.bideafactory.lab.testhomerent.message.schemas.request.discountapi.NewRequest;
import com.bideafactory.lab.testhomerent.message.schemas.request.internal.BookRequest;
import com.bideafactory.lab.testhomerent.message.schemas.response.discountapi.NewResponse;
import com.bideafactory.lab.testhomerent.model.daos.BookDao;
import com.bideafactory.lab.testhomerent.model.entities.Book;
import com.bideafactory.lab.testhomerent.util.Constant;
import com.bideafactory.lab.testhomerent.util.HomeRentUtil;
import com.bideafactory.lab.testhomerent.util.exceptions.InvalidDiscountCodeException;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service @Slf4j @RequiredArgsConstructor public class BookService {

    @Setter(onMethod_ = @Autowired) private DiscountApiAdapter discountApiAdapter;

    private final BookDao bookDao;

    /**
     * Método que crea el book
     *
     * @param request Datos del book
     * @return book creado en la base de datos
     */
    public Mono<Book> createBook(BookRequest request) {
        Mono<NewResponse> discount = Mono.just(
            (String) (request.getDiscountCode() != null ?
                request.getDiscountCode() :
                Constant.EMPTY_STRING)).flatMap(discountCode -> {
            // Se valida que exista el código de descuento para invocar a la API Discount
            if (!discountCode.isEmpty()) {
                NewRequest newDiscountRequestPayload = NewRequest.builder()
                    .discountCode(discountCode).userId(request.getId())
                    .houseId(request.getHouseId()).build();
                return discountApiAdapter.postNew(newDiscountRequestPayload);
            }
            return Mono.empty();
        });

        // Si el status es false entonces el código de descuento no es válido, se lanza error
        return discount.flatMap(disc -> Boolean.FALSE.equals(disc.getStatus()) ?
            Mono.error(new InvalidDiscountCodeException()) :
            Mono.just(disc)).flatMap(disc -> saveBookIntoDb(
            HomeRentUtil.transformBookFromBookRequest(request)))
            .switchIfEmpty(saveBookIntoDb(HomeRentUtil.transformBookFromBookRequest(request)));
    }

    private Mono<Book> saveBookIntoDb(Book book) {
        return Mono.just(book).map(b -> {
            if (b.getDiscountCode() != null && b.getDiscountCode().isEmpty()) {
                b.setDiscountCode(null);
            }
            return b;
        }).flatMap(bookDao::save);
    }
}
