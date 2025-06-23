package com.serenitybdd.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class CommonElements {

    public static final Target PAGE_TITLE = Target.the("título de la página")
            .located(By.className("title"));

}
