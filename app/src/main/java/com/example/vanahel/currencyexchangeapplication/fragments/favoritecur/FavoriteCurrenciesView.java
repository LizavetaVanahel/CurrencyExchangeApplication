package com.example.vanahel.currencyexchangeapplication.fragments.favoritecur;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;

public interface FavoriteCurrenciesView  {

    void showError(String error);

    void showSelectedCurrencyAndRate (CurrencyAndRate currencyAndRate);

}
