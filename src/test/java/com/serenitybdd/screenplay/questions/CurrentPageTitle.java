package com.serenitybdd.screenplay.questions;

import com.serenitybdd.screenplay.ui.CommonElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CurrentPageTitle implements Question<String> {

    private CurrentPageTitle() {}

    public static CurrentPageTitle value() {
        return new CurrentPageTitle();
    }

    @Override
    public String answeredBy(Actor actor) {
        return CommonElements.PAGE_TITLE.resolveFor(actor).getText();
    }
}
