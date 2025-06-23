package com.serenitybdd.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutElements {

    public static final Target FIRST_NAME_FIELD = Target.the("campo de nombre")
            .located(By.id("first-name"));

    public static final Target LAST_NAME_FIELD = Target.the("campo de apellido")
            .located(By.id("last-name"));

    public static final Target POSTAL_CODE_FIELD = Target.the("campo de código postal")
            .located(By.id("postal-code"));

    public static final Target CONTINUE_BUTTON = Target.the("botón continuar")
            .located(By.id("continue"));

    public static final Target FINISH_BUTTON = Target.the("botón finalizar")
            .located(By.id("finish"));

    public static final Target SUBTOTAL_LABEL = Target.the("etiqueta de subtotal")
            .located(By.className("summary_subtotal_label"));

    public static final Target TOTAL_LABEL = Target.the("etiqueta de total")
            .located(By.className("summary_total_label"));

    public static final Target CONFIRMATION_HEADER = Target.the("encabezado de confirmación")
            .located(By.className("complete-header"));

}
