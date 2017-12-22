package com.example.vanahel.currencyexchangeapplication.util.currencylist;

import android.content.Context;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;

import java.util.ArrayList;
import java.util.List;

import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.BEL;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.ENG;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.RUS;

/**
 * Created by Liza Kaliada on 10.12.17.
 */

public class CurrencyListDisplayer {

    private CurrentLocalizationIdProvider currentLocalizationIdProvider;
    private String languageId;

    public CurrencyListDisplayer(Context context) {
        currentLocalizationIdProvider = new CurrentLocalizationIdProvider(context);
        languageId = currentLocalizationIdProvider.provideCurrentLocalizationId();
    }

    public List<String>  showCurrencyList(List<CurrencyAndRate> currenciesAndRates) {

        List<String> currencyNameList = new ArrayList<>();

        for (CurrencyAndRate currency : currenciesAndRates) {
            switch (languageId) {
                case RUS:
                    currencyNameList.add(currency.getCurrency().getCurName());
                    break;
                case BEL:
                    currencyNameList.add(currency.getCurrency().getCurNameBel());
                    break;
                case ENG:
                    currencyNameList.add(currency.getCurrency().getCurNameEng());
                    break;
            }

        }

        return currencyNameList;

    }


    public List<CurrencyNameAndRateValue> showCurrencyAndRateList(List<CurrencyAndRate> currenciesAndRates) {

        CurrencyNameAndRateValue currencyNameAndRateValue = null;
        List<CurrencyNameAndRateValue> currencyNameAndRateValueList = new ArrayList<>();

        for (CurrencyAndRate currencyAndRate : currenciesAndRates) {
            switch (this.languageId) {
                case RUS:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                            getCurrency().getCurName(), currencyAndRate.getRate());
                    break;
                case BEL:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                            getCurrency().getCurNameBel(), currencyAndRate.getRate());
                    break;
                case ENG:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                            getCurrency().getCurNameEng(), currencyAndRate.getRate());
                    break;

            }

            currencyNameAndRateValueList.add(currencyNameAndRateValue);
        }

        return currencyNameAndRateValueList;

    }

    public CurrencyNameAndRateValue showCurrencyAndRate (CurrencyAndRate currencyAndRate){

        CurrencyNameAndRateValue currencyNameAndRateValue = null;

        switch (languageId){
            case RUS:
                currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                        getCurrency().getCurName(), currencyAndRate.getRate());
                break;
            case BEL:
                currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                        getCurrency().getCurNameBel(), currencyAndRate.getRate());
                break;
            case ENG:
                currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.
                        getCurrency().getCurNameEng(), currencyAndRate.getRate());
                break;
        }

        return currencyNameAndRateValue;
    }

}
