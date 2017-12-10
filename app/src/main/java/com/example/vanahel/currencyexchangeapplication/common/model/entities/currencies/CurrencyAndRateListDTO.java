package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyAndRateListDTO {

    private final List<Rate> ratesList;
    private final List<Currency> currencyList;

    public CurrencyAndRateListDTO(List<Rate> ratesList, List<Currency> currencyList){
        this.ratesList = ratesList;
        this.currencyList = currencyList;
    }

    public List<Rate> getRatesList() {
        return this.ratesList;
    }

    public List<Currency> getCurrencyList() {
        return this.currencyList;
    }

    public Map<Integer, Currency> getCurrencyMap(){

        Map<Integer, Currency> currenciesMap = new HashMap<>();

        for ( Currency currency : this.currencyList) {
          currenciesMap.put(currency.getCurID(), currency);
        }

        return currenciesMap;
    }

    public Map<Integer, Rate> getRatesMap(){

        Map<Integer, Rate> ratesMap = new HashMap<>();

        for ( Rate rate : this.ratesList) {
            ratesMap.put(rate.getCurID(), rate);
        }

        return ratesMap;
    }
}
