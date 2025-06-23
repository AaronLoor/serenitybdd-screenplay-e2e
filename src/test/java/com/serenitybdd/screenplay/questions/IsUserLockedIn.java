package com.serenitybdd.screenplay.questions;

import com.serenitybdd.screenplay.data.TestDataUtils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.serenitybdd.screenplay.ui.LoginElements.ERROR_MESSAGE;

public class IsUserLockedIn implements Question<Boolean> {

    private IsUserLockedIn() {}

    public static IsUserLockedIn successfully() {
        return new IsUserLockedIn();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return ERROR_MESSAGE.resolveFor(actor).isVisible() &&
                    ERROR_MESSAGE.resolveFor(actor).getText().toLowerCase().contains(TestDataUtils.Messages.LOCKED.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
