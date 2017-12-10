package com.example.vanahel.currencyexchangeapplication.common.view;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;

import java.util.List;

/**
 * Created by Liza Kaliada on 10.12.17.
 */

public interface CurrencyListView {

    void showCurrencyAndRate(List<CurrencyAndRate> currenciesAndRates );

}
