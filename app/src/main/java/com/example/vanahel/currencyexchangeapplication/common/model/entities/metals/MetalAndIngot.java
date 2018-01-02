package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

public class MetalAndIngot {

    private final Metal metal;
    private final double nominal;
    private final double price;

    public MetalAndIngot ( Metal metal, double nominal, double price ){
        this.metal = metal;
        this.nominal = nominal;
        this.price = price;
    }

    public Metal getMetal() {
        return metal;
    }

    public double getNominal() {
        return nominal;
    }

    public double getPrice() {
        return price;
    }
}
