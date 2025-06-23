package com.serenitybdd.screenplay.questions;

import com.serenitybdd.screenplay.data.TestDataUtils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.serenitybdd.screenplay.ui.InventoryElements.PAGE_TITLE;

public class IsUserLoggedIn implements Question<Boolean> {

    private IsUserLoggedIn() {}

    public static IsUserLoggedIn successfully() {
        return new IsUserLoggedIn();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return PAGE_TITLE.resolveFor(actor).getText().equals(TestDataUtils.Products.PRODUCTS);
        } catch (Exception e) {
            return false;
        }
    }
}
