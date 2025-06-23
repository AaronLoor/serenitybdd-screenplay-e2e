package com.serenitybdd.screenplay.stepdefinitions;

import com.serenitybdd.screenplay.base.BaseClass;
import com.serenitybdd.screenplay.data.TestDataUtils;
import com.serenitybdd.screenplay.helpers.LoginStepHelper;
import com.serenitybdd.screenplay.models.UserCredentials;
import com.serenitybdd.screenplay.tasks.NavigateTo;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class LoginStepDefinitions extends BaseClass {

    private final LoginStepHelper loginHelper = LoginStepHelper.getInstance();

    @Dado("que el usuario está en la página de login")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        logDemoProgress("🎬 Navegando a la página de login...");

        var actor = getOrCreateActor();
        actor.attemptsTo(NavigateTo.theSwagLabsPage());
        context.setCurrentActor(actor);
        loginHelper.clearPreviousLoginState();

        logDemoProgress("✅ En página de login: %s".formatted(getBaseUrl()));
    }

    @Cuando("el usuario se autentica en el sistema")
    public void elUsuarioSeAutenticaEnElSistema() {
        logDemoProgress("🔐 Autenticando usuario con credenciales por defecto...");

        var actor = context.getCurrentActor();
        var credentials = TestDataUtils.getCredentials();
        loginHelper.performLoginAndValidate(actor, credentials, true);

        logDemoProgress("✅ Usuario autenticado: %s".formatted(credentials.getUsername()));
    }

    @Cuando("el usuario se autentica con {string} usando password {string}")
    public void elUsuarioSeAutenticaConUsandoPassword(String user, String password) {
        logDemoProgress("🔐 Autenticando con credenciales específicas: %s".formatted(user));

        var actor = context.getCurrentActor();
        var credentials = UserCredentials.builder()
                .username(user)
                .password(password)
                .build();
        loginHelper.performLogin(actor, credentials);

        logDemoProgress("✅ Intento de login realizado para: %s".formatted(user));
    }

    @Entonces("el usuario debería ver la pantalla de inventario con productos")
    public void elUsuarioDeberiaVerLaPantallaDeInventarioConProductos() {
        logDemoProgress("✅ Verificando acceso exitoso al inventario...");

        var actor = context.getCurrentActor();

        loginHelper.validateSuccessfulLogin(actor);

        var credentials = context.getCurrentUserCredentials();
        logDemoProgress("✅ Usuario autenticado exitosamente: %s".formatted(credentials.getUsername()));
    }

    @Entonces("el usuario no debería ver la pantalla de inventario con productos")
    public void elUsuarioNoDeberiaVerLaPantallaDeInventarioConProductos() {
        logDemoProgress("🚫 Verificando que el acceso fue bloqueado...");

        var actor = context.getCurrentActor();

        loginHelper.validateBlockedLogin(actor);

        var credentials = context.getCurrentUserCredentials();
        logDemoProgress("✅ Acceso correctamente bloqueado para: %s".formatted(credentials.getUsername()));
    }
}