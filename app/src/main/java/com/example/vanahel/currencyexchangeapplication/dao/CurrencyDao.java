package com.example.vanahel.currencyexchangeapplication.dao;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;

import java.util.List;

public interface CurrencyDao {

    void save(CurrencyNameAndRateValue currencyNameAndRateValue);

    List<CurrencyNameAndRateValue> getCurrenciesAndRates();

    void delete(CurrencyNameAndRateValue currencyNameAndRateValue);

    List<Integer> getFavoriteCurrencyIds();

    List<Double> getRates();

}