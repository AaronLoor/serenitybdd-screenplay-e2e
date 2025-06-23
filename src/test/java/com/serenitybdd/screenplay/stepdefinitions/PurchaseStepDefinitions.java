package com.serenitybdd.screenplay.stepdefinitions;

import com.serenitybdd.screenplay.models.UserInformation;
import com.serenitybdd.screenplay.questions.ConfirmationMessage;
import com.serenitybdd.screenplay.questions.CurrentPageTitle;
import com.serenitybdd.screenplay.base.BaseClass;
import com.serenitybdd.screenplay.data.TestDataUtils;
import com.serenitybdd.screenplay.helpers.PurchaseStepHelper;
import com.serenitybdd.screenplay.tasks.AddProductToCart;
import com.serenitybdd.screenplay.tasks.FillCheckoutInformation;
import com.serenitybdd.screenplay.tasks.FinishPurchase;
import com.serenitybdd.screenplay.tasks.ProceedToCheckout;
import com.serenitybdd.screenplay.tasks.ViewShoppingCart;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.ensure.Ensure;


public class PurchaseStepDefinitions extends BaseClass {

    private final PurchaseStepHelper purchaseHelper = PurchaseStepHelper.getInstance();

    @Y("el usuario agrega un producto carrito")
    public void elUsuarioAgregaUnProductoAlCarrito() {
        logDemoProgress("🛒 Agregando un producto al carrito...");

        var products = TestDataUtils.getProducts();
        var producto1 = products.get(0).getName();
        var actor = context.getCurrentActor();

        actor.attemptsTo(AddProductToCart.named(producto1));
        purchaseHelper.verificarProductoAgregadoAlCarrito(actor);
        purchaseHelper.agregarProductoAlContexto(producto1);

        logDemoProgress("✅ Producto agregado: '%s'".formatted(producto1));
        purchaseHelper.logProductoAgregado(producto1, false);
    }

    @Y("el usuario visualiza el carrito de compras")
    public void elUsuarioVisualizaElCarritoDeCompras() {
        logDemoProgress("👀 Visualizando carrito...");

        var actor = context.getCurrentActor();
        actor.attemptsTo(ViewShoppingCart.now());
        actor.attemptsTo(Ensure.that(CurrentPageTitle.value()).isEqualTo("Your Cart"));

        logDemoProgress("✅ Carrito visualizado - %d productos"
                .formatted(purchaseHelper.getCurrentProductCount()));
    }

    @Y("el usuario completa el formulario de compra")
    public void elUsuarioCompletaElFormularioDeCompra() {
        logDemoProgress("📝 Completando el formulario de compra...");

        var actor = context.getCurrentActor();
        var userInfo = TestDataUtils.getCheckoutUser();

        actor.attemptsTo(ProceedToCheckout.now(), FillCheckoutInformation.with(userInfo));
        context.setCurrentUserInformation(userInfo);

        logDemoProgress("✅ Formulario completado para: %s %s"
                .formatted(userInfo.getFirstName(), userInfo.getLastName()));
    }

    @Y("el usuario finaliza la compra")
    public void elUsuarioFinalizaLaCompra() {
        logDemoProgress("💳 Finalizando compra...");

        var actor = context.getCurrentActor();
        actor.attemptsTo(FinishPurchase.now());
        purchaseHelper.marcarCheckoutCompletado();

        logDemoProgress("✅ Compra finalizada");
    }

    @Entonces("el usuario debería ver la confirmación de compra exitosa")
    public void elUsuarioDeberiaVerLaConfirmacionDeCompraExitosa() {
        logDemoProgress("✅ Verificando la confirmación de compra...");

        var actor = context.getCurrentActor();
        var expectedMessage = TestDataUtils.Messages.PURCHASE_CONFIRMATION;

        actor.attemptsTo(Ensure.that(ConfirmationMessage.text()).containsIgnoringCase(expectedMessage));
        purchaseHelper.marcarCompraCompletada();

        logDemoProgress("✅ Confirmación verificada: '%s'".formatted(expectedMessage));
        purchaseHelper.logCompraCompletada();
    }

    @Y("el usuario agrega {string} al carrito")
    public void elUsuarioAgregaAlCarrito(String producto) {
        logDemoProgress("🛒 Agregando producto específico: %s".formatted(producto));

        var actor = context.getCurrentActor();
        actor.attemptsTo(AddProductToCart.named(producto));
        purchaseHelper.verificarProductoAgregadoAlCarrito(actor);
        purchaseHelper.agregarProductoAlContexto(producto);

        logDemoProgress("✅ Producto agregado: %s".formatted(producto));
        purchaseHelper.logProductoAgregado(producto, true);
    }

    @Y("el usuario completa el formulario de compra con nombres {string}, apellidos {string} y código postal {string}")
    public void elUsuarioCompletaElFormularioDeCompraConNombresApellidosYCodigoPostal(
            String nombre, String apellido, String postalCode) {
        logDemoProgress("📝 Completando formulario con datos específicos: %s, %s, %s"
                .formatted(nombre, apellido, postalCode));

        var actor = context.getCurrentActor();
        var userInfo = UserInformation.builder()
                .firstName(nombre)
                .lastName(apellido)
                .postalCode(postalCode)
                .build();

        actor.attemptsTo(ProceedToCheckout.now(), FillCheckoutInformation.with(userInfo));
        context.setCurrentUserInformation(userInfo);

        logDemoProgress("✅ Formulario completado: %s %s (CP: %s)"
                .formatted(nombre, apellido, postalCode));
    }

    @Entonces("el usuario debería ver la confirmación {string}")
    public void elUsuarioDeberiaVerLaConfirmacion(String expectedMessage) {
        logDemoProgress("✅ Verificando confirmación específica: %s".formatted(expectedMessage));

        var actor = context.getCurrentActor();
        actor.attemptsTo(Ensure.that(ConfirmationMessage.text()).containsIgnoringCase(expectedMessage));
        purchaseHelper.marcarCompraCompletada();

        logDemoProgress("✅ Confirmación verificada: '%s'".formatted(expectedMessage));
        purchaseHelper.logCompraCompletada();
    }
}