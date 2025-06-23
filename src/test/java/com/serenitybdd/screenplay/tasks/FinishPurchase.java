package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.ui.CheckoutElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class FinishPurchase implements Task {

    public FinishPurchase() {}

    public static FinishPurchase now() {
        return new FinishPurchase();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CheckoutElements.FINISH_BUTTON)
        );
    }
}
