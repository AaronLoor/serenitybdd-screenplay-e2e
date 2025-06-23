package com.serenitybdd.screenplay.base;

import com.serenitybdd.screenplay.config.ConfigurationManager;
import com.serenitybdd.screenplay.context.TestContext;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseClass {

    protected final ConfigurationManager config = ConfigurationManager.getInstance();

    protected final TestContext context = TestContext.getInstance();

    /**
     * Obtiene la URL base según el ambiente configurado
     */
    protected String getBaseUrl() {
        return config.getBaseUrl();
    }

    protected Actor getOrCreateActor() {
        if (context.hasActor()) {
            return context.getCurrentActor();
        }
        var actor = OnStage.theActorCalled("Usuario Aaron");
        context.setCurrentActor(actor);

        return actor;
    }

    protected void logDemoProgress(String message) {
        var timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] \uD83C\uDFAD DEMO: %s%n", timestamp, message);
    }
}