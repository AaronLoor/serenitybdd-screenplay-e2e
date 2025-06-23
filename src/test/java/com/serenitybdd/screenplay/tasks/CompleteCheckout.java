package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.ui.CheckoutElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompleteCheckout implements Task {

    private CompleteCheckout() {}

    public static CompleteCheckout now() {
        return new CompleteCheckout();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutElements.FINISH_BUTTON, isVisible()),
                WaitUntil.the(CheckoutElements.FINISH_BUTTON, isClickable())
        );
    }
}
