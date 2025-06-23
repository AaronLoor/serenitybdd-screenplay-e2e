package com.serenitybdd.screenplay.tasks;

import com.serenitybdd.screenplay.config.ConfigurationManager;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Task para navegación que usa ConfigurationManager para URLs dependientes del ambiente
 * Implementa el patrón Task puro de Screenplay
 */
public class NavigateTo implements Task {

    private final String targetUrl;
    private static final ConfigurationManager config = ConfigurationManager.getInstance();

    public NavigateTo(String url) {
        this.targetUrl = url;
    }

    @Override
    @Step("{0} navega a la URL: {1}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(targetUrl));
    }

    /**
     * Navega a la página principal de Swag Labs usando la URL del ambiente configurado
     */
    @Step("{0} abre la página principal de Swag Labs")
    public static NavigateTo theSwagLabsPage() {
        return instrumented(NavigateTo.class, config.getBaseUrl());
    }

    @Override
    public String toString() {
        return "NavigateTo URL: " + targetUrl;
    }
}