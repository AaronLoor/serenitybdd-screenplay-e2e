# Serenity BDD Screenplay e2e - Swag Labs

Proyecto de automatización de pruebas end-to-end utilizando Serenity BDD con el patrón Screenplay para la aplicación Swag Labs.

## 🛠️ Tecnologías

- **Java 17**
- **Serenity BDD 4.2.22**
- **Cucumber**
- **Maven**
- **JUnit 5**
- **Screenplay Pattern**

## 📋 Prerequisitos

- Java 17 o superior
- Maven 3.6+
- Chrome/Firefox/Edge browser

## 🚀 Instalación

1. Instala las dependencias:
```bash
mvn clean install -DskipTests
```

## ▶️ Ejecución de Pruebas

### Ejecutar todas las pruebas
```bash
mvn clean verify
```

### Ejecutar con tags específicos
```bash
mvn clean verify -Dcucumber.filter.tags="@compra-dos-productos"

```

## 📊 Reportes

Después de ejecutar las pruebas, los reportes se generan automáticamente en:
```
target/site/serenity/index.html
```

Para generar reportes manualmente:
```bash
mvn serenity:aggregate
```

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.