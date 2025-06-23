package com.serenitybdd.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class LoginElements {

    public static final Target USERNAME_FIELD = Target.the("campo de usuario")
            .located(By.id("user-name"));

    public static final Target PASSWORD_FIELD = Target.the("campo de contraseña")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON = Target.the("botón de login")
            .located(By.id("login-button"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));
}
