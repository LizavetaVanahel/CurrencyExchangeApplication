package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

public class CurrencyAndRate {

    private final Currency currency;
    private final double rate;

    public CurrencyAndRate (Currency currency, double rate){
        this.currency = currency;
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }

    public int getId () { return currency.getCurID(); }

}
