package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

public class MetalNameAndIngotValue {

    private final String metalName;
    private final double nominal;
    private final double price;

    public MetalNameAndIngotValue( String metalName, double nominal, double price ) {
        this.metalName = metalName;
        this.nominal = nominal;
        this.price = price;
    }

    public String getMetalName() {
        return metalName;
    }

    public double getNominal() {
        return nominal;
    }

    public double getPrice() {
        return price;
    }
}
