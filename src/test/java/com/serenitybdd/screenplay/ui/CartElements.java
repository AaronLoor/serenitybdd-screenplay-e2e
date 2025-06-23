package com.serenitybdd.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class CartElements {

    public static final Target CHECKOUT_BUTTON = Target.the("botón de checkout")
            .located(By.id("checkout"));

}
