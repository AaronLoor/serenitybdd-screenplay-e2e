# language: es
Característica: Proceso de Compra de Productos en Swag Labs

  Como cliente de la tienda online Swag Labs
  Quiero poder seleccionar productos, añadirlos al carrito y completar el proceso de pago
  Para así adquirir los artículos que deseo de forma exitosa

  Antecedentes:
    Dado que el usuario está en la página de login

  @test @compra @compra-un-producto
  Escenario: Realizar una compra exitosa de un producto
    Cuando el usuario se autentica en el sistema
    Y el usuario agrega un producto carrito
    Y el usuario visualiza el carrito de compras
    Y el usuario completa el formulario de compra
    Y el usuario finaliza la compra
    Entonces el usuario debería ver la confirmación de compra exitosa

  @test @compra @compra-dos-productos
  Esquema del escenario: Realizar una compra exitosa de dos productos
    Cuando el usuario se autentica en el sistema
    Y el usuario agrega <producto1> al carrito
    Y el usuario agrega <producto2> al carrito
    Y el usuario visualiza el carrito de compras
    Y el usuario completa el formulario de compra con nombres <nombre>, apellidos <apellido> y código postal <postalCode>
    Y el usuario finaliza la compra
    Entonces el usuario debería ver la confirmación <mensajeConfirmacion>

    Ejemplos:
      | producto1                 | producto2                  | nombre  | apellido   | postalCode | mensajeConfirmacion         |
      | "Sauce Labs Backpack"     | "Sauce Labs Bike Light"    | "Juan"  | "Pérez"    | "45623"    | "Thank you for your order!" |
      | "Sauce Labs Bolt T-Shirt" | "Sauce Labs Fleece Jacket" | "Mario" | "Zambrano" | "54312"    | "Thank you for your order!" |