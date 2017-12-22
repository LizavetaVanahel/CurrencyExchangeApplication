package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

public class CurrencyNameAndRateValue {

    private final String curName;
    private final Double rate;

    public Integer getId() {
        return id;
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
        return curName;
    }

    public Double getRate() {
        return rate;
    }


}
