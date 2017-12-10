package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

public class MetalNameAndIngotValue {

    private final String metalName;
    private final Double nominal;
    private final Double price;

    public MetalNameAndIngotValue(String metalName, Double nominal, Double price ) {
        this.metalName = metalName;
        this.nominal = nominal;
        this.price = price;
    }

    public String getMetalName() {
        return this.metalName;
    }

    public Double getNominal() {
        return this.nominal;
    }

    public Double getPrice() {
        return this.price;
    }
}
