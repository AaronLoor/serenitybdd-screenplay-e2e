package com.serenitybdd.screenplay.questions;

import com.serenitybdd.screenplay.ui.InventoryElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CartItemCount implements Question<Integer> {

    private CartItemCount() {}

    public static CartItemCount value() {
        return new CartItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        try {
            String badgeText = InventoryElements.CART_BADGE.resolveFor(actor).getText();
            return Integer.parseInt(badgeText);
        } catch (Exception e) {
            return 0;
        }
    }
}
