package com.serenitybdd.screenplay.models;

public class Product {
    private final String name;

    private Product(Builder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + "'}";
    }
}
