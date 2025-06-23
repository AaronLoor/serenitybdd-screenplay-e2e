package com.serenitybdd.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class InventoryElements {

    public static final Target PAGE_TITLE = Target.the("título de la página")
            .located(By.className("title"));

    public static final Target INVENTORY_ITEMS = Target.the("elementos del inventario")
            .located(By.className("inventory_item"));

    public static final Target SHOPPING_CART_LINK = Target.the("enlace del carrito")
            .located(By.className("shopping_cart_link"));

    public static final Target CART_BADGE = Target.the("badge del carrito")
            .located(By.className("shopping_cart_badge"));

    public static Target addToCartButtonFor(String productName) {
        return Target.the("botón agregar al carrito para " + productName)
                .located(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]"));
    }

    public static Target productByName(String productName) {
        return Target.the("producto " + productName)
                .located(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
    }
}
