package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.ui.InventoryElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    private final String productName;

    private AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return new AddProductToCart(productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(InventoryElements.addToCartButtonFor(productName), isVisible()),
                Click.on(InventoryElements.addToCartButtonFor(productName))
        );
    }
}
