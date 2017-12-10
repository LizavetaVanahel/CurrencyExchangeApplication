package com.example.vanahel.currencyexchangeapplication.fragments.curgraphic;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;

import java.util.List;
import java.util.Map;

public interface CurrencyGraphicView {

    void showGraphic(Map<Integer, Float> rateDynamics);

    void showError (String error);

    void showCurrencyAndRate (List<CurrencyAndRate> currenciesAndRates);

}
