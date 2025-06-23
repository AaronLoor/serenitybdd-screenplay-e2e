package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.ui.InventoryElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewShoppingCart implements Task {

    public ViewShoppingCart() {}

    public static ViewShoppingCart now() {
        return new ViewShoppingCart();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println("🛒 Navegando al carrito de compras...");

        actor.attemptsTo(
                WaitUntil.the(InventoryElements.SHOPPING_CART_LINK, isVisible()),
                Click.on(InventoryElements.SHOPPING_CART_LINK)
        );

        System.out.println("✅ Navegación al carrito completada");
    }
}