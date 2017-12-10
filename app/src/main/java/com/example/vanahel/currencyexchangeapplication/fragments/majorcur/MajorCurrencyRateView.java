package com.example.vanahel.currencyexchangeapplication.fragments.majorcur;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;

import java.util.List;

public interface MajorCurrencyRateView {

    void showCurrentDate (CharSequence currentDate);

    void showCurrencyAndRate (List<CurrencyAndRate> currenciesAndRates);

    void showError(String error);
}
