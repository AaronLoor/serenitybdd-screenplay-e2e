package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.models.UserInformation;
import com.serenitybdd.screenplay.ui.CheckoutElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillCheckoutInformation implements Task {

    private final UserInformation userInformation;

    private FillCheckoutInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public static FillCheckoutInformation with(UserInformation userInformation) {
        return new FillCheckoutInformation(userInformation);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutElements.FIRST_NAME_FIELD, isVisible()),
                Enter.theValue(userInformation.getFirstName()).into(CheckoutElements.FIRST_NAME_FIELD),
                Enter.theValue(userInformation.getLastName()).into(CheckoutElements.LAST_NAME_FIELD),
                Enter.theValue(userInformation.getPostalCode()).into(CheckoutElements.POSTAL_CODE_FIELD),
                Click.on(CheckoutElements.CONTINUE_BUTTON)
        );
    }
}
