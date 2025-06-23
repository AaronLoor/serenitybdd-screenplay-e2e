package com.serenitybdd.screenplay.hooks;

import com.serenitybdd.screenplay.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class TestHooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n🎬 INICIANDO ESCENARIO: " + scenario.getName());

        OnStage.setTheStage(new OnlineCast());
        TestContext.reset();

        System.out.println("✅ Configuración inicial completada");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("🎬 FINALIZANDO ESCENARIO: " + scenario.getName());
        System.out.println("📊 Estado: " + (scenario.isFailed() ? "❌ FALLIDO" : "✅ EXITOSO"));

        TestContext testContext = TestContext.getInstance();
        if (testContext != null) {
            System.out.println("🧹 Limpiando contexto...");
            testContext.clear();
        }

        OnStage.drawTheCurtain();
        System.out.println("✅ Limpieza completada\n");
    }
}
