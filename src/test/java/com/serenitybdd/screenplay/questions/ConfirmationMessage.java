package com.serenitybdd.screenplay.questions;

import com.serenitybdd.screenplay.ui.CheckoutElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ConfirmationMessage implements Question<String> {

    public ConfirmationMessage() {}

    public static ConfirmationMessage text() {
        return new ConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return CheckoutElements.CONFIRMATION_HEADER.resolveFor(actor).getText();
    }
}
