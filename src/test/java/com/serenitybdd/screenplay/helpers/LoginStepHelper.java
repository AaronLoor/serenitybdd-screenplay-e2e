package com.serenitybdd.screenplay.helpers;

import com.serenitybdd.screenplay.context.ContextKeys;
import com.serenitybdd.screenplay.context.TestContext;
import com.serenitybdd.screenplay.models.UserCredentials;
import com.serenitybdd.screenplay.questions.IsUserLoggedIn;
import com.serenitybdd.screenplay.questions.IsUserLockedIn;
import com.serenitybdd.screenplay.tasks.Login;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.Optional;

/**
 * Helper Singleton para operaciones de login en Screenplay
 * Patrón: Singleton Thread-Safe + Helper
 */
public class LoginStepHelper {

    private static final ThreadLocal<LoginStepHelper> instance = new ThreadLocal<>();

    private LoginStepHelper() {}

    public static LoginStepHelper getInstance() {
        if (instance.get() == null) {
            instance.set(new LoginStepHelper());
        }
        return instance.get();
    }

    public static void reset() {
        instance.remove();
    }


    /**
     * Realiza login y valida el resultado inmediatamente
     */
    public void performLoginAndValidate(Actor actor, UserCredentials credentials, boolean shouldSucceed) {
        performLogin(actor, credentials);

        if (shouldSucceed) {
            actor.attemptsTo(Ensure.that(IsUserLoggedIn.successfully()).isEqualTo(true));
            getContext().put(ContextKeys.LOGIN_SUCCESS, Boolean.TRUE);
        }
    }

    /**
     * Realiza solo el login sin validación (para esquemas de escenario)
     */
    public void performLogin(Actor actor, UserCredentials credentials) {
        actor.attemptsTo(Login.withCredentials(credentials));
        getContext().setCurrentUserCredentials(credentials);
        getContext().put(ContextKeys.CURRENT_LOGIN_ATTEMPT, credentials.getUsername());
    }

    /**
     * Valida login exitoso
     */
    public void validateSuccessfulLogin(Actor actor) {
        var credentials = getContext().getCurrentUserCredentials();
        actor.attemptsTo(Ensure.that(IsUserLoggedIn.successfully()).isEqualTo(true));
        getContext().put(ContextKeys.LOGIN_SUCCESS, Boolean.TRUE);
        logLoginSuccess(credentials);
    }

    /**
     * Valida login bloqueado
     */
    public void validateBlockedLogin(Actor actor) {
        var credentials = getContext().getCurrentUserCredentials();

        actor.attemptsTo(Ensure.that(IsUserLockedIn.successfully()).isEqualTo(true));
        actor.attemptsTo(Ensure.that(IsUserLoggedIn.successfully()).isEqualTo(false));

        getContext().put(ContextKeys.LOGIN_SUCCESS, Boolean.FALSE);
        getContext().put(ContextKeys.LOGIN_FAILURE_REASON, "Usuario bloqueado");

        logLoginFailure(credentials, "Usuario bloqueado por el sistema");
    }

    /**
     * Limpia el estado previo de login para un nuevo escenario
     */
    public void clearPreviousLoginState() {
        var context = getContext();
        context.remove(ContextKeys.LOGIN_SUCCESS);
        context.remove(ContextKeys.LOGIN_FAILURE_REASON);
        context.remove(ContextKeys.CURRENT_LOGIN_ATTEMPT);
    }


    private void logLoginSuccess(UserCredentials credentials) {
        System.out.println("🎉 LOGIN EXITOSO");
        System.out.println("👤 Usuario: " + credentials.getUsername());
        System.out.println("📊 Estado del contexto: " + getContext().toString());
    }

    private void logLoginFailure(UserCredentials credentials, String reason) {
        System.out.println("❌ LOGIN FALLIDO");
        System.out.println("👤 Usuario: " + credentials.getUsername());
        System.out.println("🚫 Razón: " + reason);
        System.out.println("📊 Estado del contexto: " + getContext().toString());
    }

    public boolean isLoginSuccessful() {
        return Optional.ofNullable(getContext().getBoolean(ContextKeys.LOGIN_SUCCESS)).orElse(false);
    }

    private TestContext getContext() {
        return TestContext.getInstance();
    }
}
