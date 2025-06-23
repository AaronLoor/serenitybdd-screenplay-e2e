package com.serenitybdd.screenplay.data;

import com.serenitybdd.screenplay.models.Product;
import com.serenitybdd.screenplay.models.UserCredentials;
import com.serenitybdd.screenplay.models.UserInformation;

import java.util.List;

public final class TestDataUtils {

    private TestDataUtils() {
    }


    public static class Users {
        public static final UserCredentials STANDARD_USER = UserCredentials.builder()
                .username("standard_user")
                .password("secret_sauce")
                .build();
    }

    public static class CheckoutInfo {
        public static final UserInformation DEFAULT_USER = UserInformation.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .postalCode("28001")
                .build();
    }

    public static class Credentials {
        public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
        public static final String SECRET_SAUCE_PASSWORD = "secret_sauce";
    }
    public static class Products {
        public static final String BACKPACK = "Sauce Labs Backpack";
        public static final String BIKE_LIGHT = "Sauce Labs Bike Light";
        public static final String PRODUCTS = "Products";
    }

    public static class Messages {
        public static final String PURCHASE_CONFIRMATION = "Thank you for your order!";
        public static final String LOCKED = "Locked";
    }

    public static UserCredentials getCredentials() {
        return UserCredentials.builder()
                .username(Credentials.PERFORMANCE_GLITCH_USER)
                .password(Credentials.SECRET_SAUCE_PASSWORD)
                .build();
    }

    public static List<Product> getProducts() {
        return List.of(
                Product.builder().name(Products.BACKPACK).build(),
                Product.builder().name(Products.BIKE_LIGHT).build());
    }

    public static UserInformation getCheckoutUser() {
        return UserInformation.builder()
                .firstName("Aaron")
                .lastName("Loor")
                .postalCode("777")
                .build();
    }
}