package com.example.vanahel.currencyexchangeapplication.fragments.particulardaterate;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;

import java.util.List;

public interface ParticularDateCurrencyRateView  {

    void showParticularDateRate(Double rate);

    void showError(String error);

    void setCurrenciesList( List<Currency> currenciesList);

}
