package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

public class MetalAndIngot {

    private final Metal metal;
    private final Double nominal;
    private final Double price;

    public MetalAndIngot (Metal metal, Double nominal, Double price){
        this.metal = metal;
        this.nominal = nominal;
        this.price = price;
    }

    public Metal getMetal() {
        return this.metal;
    }

    public Double getNominal() {
        return this.nominal;
    }

    public Double getPrice() {
        return this.price;
    }
}
