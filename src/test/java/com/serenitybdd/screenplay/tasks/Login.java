package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.models.UserCredentials;
import com.serenitybdd.screenplay.ui.LoginElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    private final UserCredentials credentials;

    private Login(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public static Login withCredentials(UserCredentials credentials) {
        return new Login(credentials);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginElements.USERNAME_FIELD, isVisible()),
                Enter.theValue(credentials.getUsername()).into(LoginElements.USERNAME_FIELD),
                Enter.theValue(credentials.getPassword()).into(LoginElements.PASSWORD_FIELD),
                Click.on(LoginElements.LOGIN_BUTTON)
        );
    }
}
