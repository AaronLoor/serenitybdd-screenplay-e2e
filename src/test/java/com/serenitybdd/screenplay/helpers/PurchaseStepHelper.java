package com.serenitybdd.screenplay.helpers;

import com.serenitybdd.screenplay.context.ContextKeys;
import com.serenitybdd.screenplay.context.TestContext;
import com.serenitybdd.screenplay.questions.CartItemCount;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Helper Singleton para operaciones de compra en Screenplay
 * Patrón: Singleton Thread-Safe + Helper
 */
public class PurchaseStepHelper {

    private static final ThreadLocal<PurchaseStepHelper> instance = new ThreadLocal<>();

    private PurchaseStepHelper() {}

    public static PurchaseStepHelper getInstance() {
        if (instance.get() == null) {
            instance.set(new PurchaseStepHelper());
        }
        return instance.get();
    }

    public static void reset() {
        instance.remove();
    }

    /**
     * Verifica que un producto fue agregado al carrito correctamente
     */
    public void verificarProductoAgregadoAlCarrito(Actor actor) {
        var currentCount = getCurrentProductCount();
        var expectedValue = currentCount + 1;

        actor.attemptsTo(Ensure.that(CartItemCount.value()).isEqualTo(expectedValue));
        getContext().put(ContextKeys.PRODUCT_COUNT, expectedValue);
    }

    /**
     * Agrega un producto a la lista de productos en el contexto
     */
    public void agregarProductoAlContexto(String producto) {
        var productosAgregados = getProductosAgregados();
        productosAgregados.add(producto);
        getContext().put(ContextKeys.PRODUCTS_ADDED, productosAgregados);
    }

    /**
     * Marca el checkout como completado
     */
    public void marcarCheckoutCompletado() {
        getContext().put(ContextKeys.CHECKOUT_COMPLETED, Boolean.TRUE);
    }

    /**
     * Marca la compra como completada
     */
    public void marcarCompraCompletada() {
        getContext().put(ContextKeys.PURCHASE_COMPLETED, Boolean.TRUE);
    }

    /**
     * Log completo al finalizar una compra
     */
    public void logCompraCompletada() {
        var productosAgregados = getProductosAgregados();
        var userInfo = getContext().getCurrentUserInformation();

        System.out.println("🎉 TEST COMPLETADO EXITOSAMENTE");
        System.out.println("📦 Productos comprados: " + productosAgregados.size());

        Optional.ofNullable(userInfo)
                .ifPresent(info -> System.out.println("👤 Cliente: " +
                        info.getFirstName() + " " + info.getLastName()));

        System.out.println("📊 Estado del contexto: " + getContext().toString());
    }

    /**
     * Log detallado de producto agregado
     */
    public void logProductoAgregado(String producto, boolean esEspecifico) {
        var tipoLog = esEspecifico ? "específico" : "aleatorio";
        System.out.println("✅ Producto " + tipoLog + " agregado: " + producto);
        System.out.println("📊 Total productos en carrito: " + getCurrentProductCount());
    }

    public List<String> getProductosAgregados() {
        var productos = getContext().get(ContextKeys.PRODUCTS_ADDED, List.class);
        return productos != null ? productos : new ArrayList<>();
    }

    public boolean isPurchaseCompleted() {
        return Optional.ofNullable(getContext().getBoolean(ContextKeys.PURCHASE_COMPLETED)).orElse(false);
    }

    public int getCurrentProductCount() {
        return Optional.ofNullable(getContext().getInteger(ContextKeys.PRODUCT_COUNT)).orElse(0);
    }

    /**
     * Obtiene información resumida de la compra
     */
    public String getResumenCompra() {
        var productos = getProductosAgregados();
        var userInfo = getContext().getCurrentUserInformation();
        var userName = userInfo != null ?
                userInfo.getFirstName() + " " + userInfo.getLastName() : "Usuario no identificado";

        return "Compra de %d productos por %s".formatted(productos.size(), userName);
    }

    /**
     * Limpia el estado de compra para un nuevo escenario
     */
    public void limpiarEstadoCompra() {
        var context = getContext();
        context.remove(ContextKeys.PRODUCTS_ADDED);
        context.remove(ContextKeys.PRODUCT_COUNT);
        context.remove(ContextKeys.CHECKOUT_COMPLETED);
        context.remove(ContextKeys.PURCHASE_COMPLETED);
    }

    private TestContext getContext() {
        return TestContext.getInstance();
    }
}