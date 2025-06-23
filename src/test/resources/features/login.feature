# language: es
Característica: Proceso de login al sitio Swag Labs

  Como cliente de la tienda online Swag Labs
  Quiero poder realizar el login a la tienda online Swag Labs
  Para así visualizar los artículos de forma exitosa

  Antecedentes:
    Dado que el usuario está en la página de login

  @test @login @login-usuario-aceptado
  Esquema del escenario: Validar login de usuario aceptado
    Cuando el usuario se autentica con <user> usando password <password>
    Entonces el usuario debería ver la pantalla de inventario con productos

    Ejemplos:
      | user          | password       |
      | "visual_user" | "secret_sauce" |


  @test @login @login-usuario-bloqueado
  Esquema del escenario: Validar login de usuario bloqueado
    Cuando el usuario se autentica con <user> usando password <password>
    Entonces el usuario no debería ver la pantalla de inventario con productos

    Ejemplos:
      | user              | password       |
      | "locked_out_user" | "secret_sauce" |