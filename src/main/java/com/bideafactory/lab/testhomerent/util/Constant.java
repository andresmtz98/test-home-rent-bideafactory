package com.bideafactory.lab.testhomerent.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Clase util para constantes
 *
 * @author Andres Martinez
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String API_DISCOUNT_BASE_URL = "https://622271e2666291106a26a17c.mockapi.io/discount/";
    public static final String API_DISCOUNT_ENDPOINT_NEW_OPERATION = "/v1/new";
    public static final String LOG_MESSAGE_REQUEST = "[REQUEST] %s [SERVICE: %s] [OPERATION: %s]";
    public static final String LOG_MESSAGE_RESPONSE = "[RESPONSE] %s [SERVICE: %s] [OPERATION: %s] [STATUS CODE: %s]";
    public static final String SERVICE_API_DISCOUNT = "Discount";
    public static final String API_DISCOUNT_NEW_OPERATION = "new";
    public static final String SERVICE_API_HOME_RENT = "HomeRent";
    public static final String API_HOME_RENT_BOOK_OPERATION = "book";
    public static final Object EMPTY_STRING = "";
    public static final int INT_NUMBER_NINE = 9;
    public static final int INT_NUMBER_TEN = 10;
    public static final int INT_NUMBER_TWO = 2;
    public static final int INT_NUMBER_FIFTY = 50;
    public static final long LONG_NUMBER_EIGHTEEN = 18;
    public static final long LONG_NUMBER_ONE_HUNDRED = 100;
    public static final int INT_NUMBER_TWENTY = 20;
    public static final int INT_NUMBER_SIX = 6;
    public static final int INT_NUMBER_FIFTEEN = 15;
    public static final int INT_NUMBER_EIGHT = 8;
    public static final int INT_NUMBER_THREE = 3;
    public static final int INT_NUMBER_FIVE = 5;
    public static final String COMMON_STRING_BOOK_ACCEPTED = "Book Accepted";
    public static final String COMMON_STRING_INVALID_DISCOUNT = "Invalid discount";
    public static final String COMMON_STRING_ERROR_MESSAGE = "Oops! We can't process your request. Try it later!";
    public static final String COMMON_STRING_SYSTEM_ERROR = "System error";
    public static final String COMMON_STRING_REQUIRED_PROPERTY = "required property '%s' %s";
    public static final String API_HOME_RENT_BOOK_ENDPOINT = "/bideafactory/book";
    public static final String COMMON_STRING_LOG_ERROR_MESSAGE = "An error occured";
}
