package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

public class CurrencyNameAndRateValue {

    private final String curName;
    private final Double rate;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public CurrencyNameAndRateValue(String curName, Double rate){
        this.curName = curName;
        this.rate = rate;
    }

    public String getCurName() {
        return this.curName;
    }

    public Double getRate() {
        return this.rate;
    }


}
