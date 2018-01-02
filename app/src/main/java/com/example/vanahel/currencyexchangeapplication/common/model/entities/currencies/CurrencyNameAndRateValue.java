package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

public class CurrencyNameAndRateValue {

    private final String curName;
    private final double rate;
    private int id;

    public CurrencyNameAndRateValue( String curName, double rate ){
        this.curName = curName;
        this.rate = rate;
    }

    public String getCurName() {
        return curName;
    }

    public double getRate() {
        return rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
