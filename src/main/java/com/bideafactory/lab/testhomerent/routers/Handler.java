package com.bideafactory.lab.testhomerent.routers;

import com.bideafactory.lab.testhomerent.message.schemas.request.internal.BookRequest;
import com.bideafactory.lab.testhomerent.message.schemas.response.internal.BookResponse;
import com.bideafactory.lab.testhomerent.message.schemas.response.internal.ErrorResponse;
import com.bideafactory.lab.testhomerent.service.BookService;
import com.bideafactory.lab.testhomerent.util.Constant;
import com.bideafactory.lab.testhomerent.util.exceptions.InvalidDiscountCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component @RequiredArgsConstructor @Slf4j public class Handler {

    private final BookService bookService;
    private final Validator validator;

    /**
     * Handler para crear un nuevo book
     *
     * @param request Request del servidor
     * @return Response del resultado del intento de creación del book
     */
    public Mono<ServerResponse> createBook(ServerRequest request) {
        log.info(
            String.format(Constant.LOG_MESSAGE_REQUEST, Constant.EMPTY_STRING,
                Constant.SERVICE_API_HOME_RENT,
                Constant.API_HOME_RENT_BOOK_OPERATION));

        Mono<BookRequest> requestBody = request.bodyToMono(BookRequest.class);
        return requestBody.flatMap(b -> validateRequest(b).switchIfEmpty(
            // Si es empty entonces el request es válido
            bookService.createBook(b).flatMap(book -> {
                log.info(
                    String.format(Constant.LOG_MESSAGE_RESPONSE, book.getId(),
                        Constant.SERVICE_API_HOME_RENT,
                        Constant.API_HOME_RENT_BOOK_OPERATION,
                        HttpStatus.OK.value()));
                return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON).body(
                        BodyInserters.fromValue(new BookResponse(
                            Constant.COMMON_STRING_BOOK_ACCEPTED,
                            HttpStatus.OK.value())));
            }))).onErrorResume(error -> {
            if (error instanceof InvalidDiscountCodeException) {
                log.info(String.format(Constant.LOG_MESSAGE_RESPONSE,
                    Constant.EMPTY_STRING, Constant.SERVICE_API_HOME_RENT,
                    Constant.API_HOME_RENT_BOOK_OPERATION,
                    HttpStatus.CONFLICT.value()));
                return ServerResponse.status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON).body(
                        BodyInserters.fromValue(new ErrorResponse(
                            Constant.COMMON_STRING_INVALID_DISCOUNT,
                            HttpStatus.CONFLICT.value(),
                            HttpStatus.CONFLICT.getReasonPhrase())));
            }
            log.error(Constant.COMMON_STRING_LOG_ERROR_MESSAGE, error);
            log.info(String.format(Constant.LOG_MESSAGE_RESPONSE,
                Constant.EMPTY_STRING, Constant.SERVICE_API_HOME_RENT,
                Constant.API_HOME_RENT_BOOK_OPERATION, HttpStatus.OK.value()));
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                    new ErrorResponse(Constant.COMMON_STRING_ERROR_MESSAGE,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        Constant.COMMON_STRING_SYSTEM_ERROR)));
        });
    }

    private Mono<ServerResponse> validateRequest(BookRequest bookRequest) {
        Errors errors = new BeanPropertyBindingResult(bookRequest,
            BookRequest.class.getName());
        validator.validate(bookRequest, errors);

        return errors.hasErrors() ?
            Flux.fromIterable(errors.getFieldErrors()).next()
                .flatMap(fieldError -> {
                    log.info(String.format(Constant.LOG_MESSAGE_RESPONSE,
                        Constant.EMPTY_STRING, Constant.SERVICE_API_HOME_RENT,
                        Constant.API_HOME_RENT_BOOK_OPERATION,
                        HttpStatus.BAD_REQUEST.value()));
                    return ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON).body(
                            BodyInserters.fromValue(new ErrorResponse(
                                String.format(
                                    Constant.COMMON_STRING_REQUIRED_PROPERTY,
                                    fieldError.getField(),
                                    fieldError.getDefaultMessage()),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase())));
                }) :
            Mono.empty();
    }
}
