package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

public class CurrencyAndRate {

    private final Currency currency;
    private final Double rate;

    public CurrencyAndRate (Currency currency, Double rate){
        this.currency = currency;
        this.rate = rate;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public Double getRate() {
        return this.rate;
    }

    public Integer getId () { return this.currency.getCurID(); }

}
