package com.bideafactory.lab.testhomerent.adapters;

import com.bideafactory.lab.testhomerent.message.schemas.request.discountapi.NewRequest;
import com.bideafactory.lab.testhomerent.message.schemas.response.discountapi.NewResponse;
import com.bideafactory.lab.testhomerent.util.Constant;
import com.bideafactory.lab.testhomerent.util.exceptions.InvalidDiscountCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.stream.Stream;

@Service @RequiredArgsConstructor @Slf4j public class DiscountApiAdapter {

    private final WebClient webClient;

    /**
     * Método que valida el código de descuento
     *
     * @param rq Parámetros del request
     * @return Datos correspondientes al descuento en caso de existir
     */
    public Mono<NewResponse> postNew(NewRequest rq) {
        log.info(String.format(Constant.LOG_MESSAGE_REQUEST, rq.getUserId(),
            Constant.SERVICE_API_DISCOUNT,
            Constant.API_DISCOUNT_NEW_OPERATION));
        return webClient.post()
            .uri(Constant.API_DISCOUNT_ENDPOINT_NEW_OPERATION)
            .httpRequest(clientHttpRequest -> {
                // Parametrización del read timeout
                HttpClientRequest httpClientRequest = clientHttpRequest.getNativeRequest();
                httpClientRequest.responseTimeout(
                    Duration.ofSeconds(Constant.INT_NUMBER_FIVE));
            }).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(rq)).exchangeToMono(rs -> {
                log.info(
                    String.format(Constant.LOG_MESSAGE_RESPONSE, rs.logPrefix(),
                        Constant.SERVICE_API_DISCOUNT,
                        Constant.API_DISCOUNT_NEW_OPERATION,
                        rs.statusCode().value()));
                if (rs.statusCode().equals(HttpStatus.CREATED)) {
                    return rs.bodyToMono(NewResponse.class);
                } else if (rs.statusCode().is5xxServerError()) {
                    return rs.createException().flatMap(Mono::error);
                } else {
                    return Mono.error(new InvalidDiscountCodeException());
                }
            }).retryWhen(Retry.max(Constant.INT_NUMBER_THREE).filter(
                // Configuración de reintentos filtrando que el error sea por problemas de conexión
                err -> Stream.of(WebClientRequestException.class,
                        WebClientResponseException.class)
                    .anyMatch(clazz -> clazz.isInstance(err))));
    }
}
