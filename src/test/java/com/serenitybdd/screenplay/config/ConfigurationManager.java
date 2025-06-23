package com.serenitybdd.screenplay.config;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.environment.WebDriverConfiguredEnvironment;

/**
 * Configuration Manager que lee directamente de serenity.conf
 * Utiliza las APIs nativas de Serenity para manejo de configuración del ambiente
 */
public class ConfigurationManager {

    private static final ThreadLocal<ConfigurationManager> instance = new ThreadLocal<>();

    private ConfigurationManager() {
        configurePageObjectPlaceholders();
    }

    public static ConfigurationManager getInstance() {
        if (instance.get() == null) {
            instance.set(new ConfigurationManager());
        }
        return instance.get();
    }

    /**
     * Limpia la instancia del ThreadLocal - importante para tests paralelos y limpieza
     */
    public static void reset() {
        instance.remove();
    }

    /**
     * Configura los placeholders para que Page Objects puedan usar URLs dinámicas
     */
    private void configurePageObjectPlaceholders() {
        try {
            System.setProperty("swagLabsUrl", getBaseUrl());
        } catch (Exception e) {
            System.err.println("⚠️ Warning: No se pudieron configurar placeholders: " + e.getMessage());
        }
    }

    /**
     * Obtiene la URL base de la aplicación desde serenity.conf según el ambiente actual
     */
    public String getBaseUrl() {
        return getDefaultAppUrlSerenityProperty();
    }

    /**
     * Obtiene una propiedad desde serenity.conf usando las APIs nativas de Serenity
     * EnvironmentSpecificConfiguration se encarga automáticamente de seleccionar el ambiente correcto
     */
    private String getDefaultAppUrlSerenityProperty() {
        try {
            var environmentVariables = WebDriverConfiguredEnvironment.getEnvironmentVariables();
            var environmentValueDefault = EnvironmentSpecificConfiguration.from(environmentVariables)
                    .getProperty("environments.default");
            var value = EnvironmentSpecificConfiguration.from(environmentVariables)
                    .getProperty("environments.".concat(environmentValueDefault).concat(".").concat("app.url"));
            return value != null ? value : "https://www.saucedemo.com/";

        } catch (Exception e) {
            var systemValue = System.getProperty("environments.default");
            if (systemValue != null) {
                return systemValue;
            }

            System.err.println("⚠️ Error accessing serenity.conf property '" + "environments.default" + "': " + e.getMessage());
            return "https://www.saucedemo.com/";
        }
    }

    /**
     * Log de configuración actual desde serenity.conf
     */
    public void logCurrentConfiguration() {
        System.out.println("🔧 CONFIGURACIÓN DESDE SERENITY.CONF:");
        System.out.println("🌍 Ambiente por defecto: " + getDefaultAppUrlSerenityProperty());
        System.out.println("🌐 URL Base: " + getBaseUrl());

        var swagLabsUrl = System.getProperty("swagLabsUrl");
        if (swagLabsUrl != null) {
            System.out.println("📄 Page Object URL: " + swagLabsUrl);
        }
    }
}