package com.bideafactory.lab.testhomerent.routers;

import com.bideafactory.lab.testhomerent.util.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration public class RestRouter {

    @Bean public RouterFunction<ServerResponse> routes(Handler handler) {
        return route(POST(Constant.API_HOME_RENT_BOOK_ENDPOINT),
            handler::createBook);
    }
}
