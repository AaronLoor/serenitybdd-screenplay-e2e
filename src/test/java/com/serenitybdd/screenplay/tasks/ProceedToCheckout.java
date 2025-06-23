package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.ui.CartElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ProceedToCheckout implements Task {

    // ✅ Constructor público (requerido para Instrumented)
    public ProceedToCheckout() {}

    public static ProceedToCheckout now() {
        return new ProceedToCheckout(); // ✅ Más simple, sin Instrumented
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println("🛒 Procediendo al checkout...");

        actor.attemptsTo(
                WaitUntil.the(CartElements.CHECKOUT_BUTTON, isClickable()),
                Click.on(CartElements.CHECKOUT_BUTTON)
        );

        System.out.println("✅ Checkout iniciado correctamente");
    }
}