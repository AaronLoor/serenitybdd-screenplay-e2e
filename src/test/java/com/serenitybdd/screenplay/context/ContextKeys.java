package com.serenitybdd.screenplay.context;

/**
 * Constantes para las claves del contexto de pruebas
 * Centraliza todas las claves para evitar duplicación y errores de tipeo
 */
public final class ContextKeys {

    private ContextKeys() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }

    // ===== LOGIN CONTEXT KEYS =====
    public static final String CURRENT_LOGIN_ATTEMPT = "current_login_attempt";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String LOGIN_FAILURE_REASON = "login_failure_reason";

    // ===== PURCHASE CONTEXT KEYS =====
    public static final String PRODUCTS_ADDED = "products_added";
    public static final String PRODUCT_COUNT = "product_count";
    public static final String CHECKOUT_COMPLETED = "checkout_completed";
    public static final String PURCHASE_COMPLETED = "purchase_completed";
}