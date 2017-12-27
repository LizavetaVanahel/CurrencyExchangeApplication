package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;
import com.example.vanahel.currencyexchangeapplication.util.pushnotservice.PushNotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.BEL;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.ENG;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.RUS;

/**
 * Created by Liza Kaliada on 10.12.17.
 */

public class FavoriteCurrencyUpdateFLow {

    private CurrencyDao currencyDao = DaoManager.getInstance().getCurrencyDao();
    private CurrencyListDisplayer currencyListDisplayer = new CurrencyListDisplayer(CurrenciesApplication.getAppContext());

    public void runUpdateFlow (List<CurrencyAndRate> favoriteCurrencyAndRate){

        sendNotification(favoriteCurrencyAndRate);
//        save(currenciesMap, ratesMap);

    }

    private void sendNotification (List<CurrencyAndRate> favoriteCurrencyAndRate ){

        PushNotificationService pushNotificationService = new PushNotificationService();

        List<Double> favoriteRatesBeforeUpdate = currencyDao.getRates();

        for ( CurrencyAndRate currencyAndRate : favoriteCurrencyAndRate ) {
            for ( Double beforeUpdateRate : favoriteRatesBeforeUpdate ) {
                if (currencyAndRate.getRate() < beforeUpdateRate) {
                    CurrencyNameAndRateValue currencyNameAndRateValue = currencyListDisplayer.showCurrencyAndRate(currencyAndRate);
                    pushNotificationService.sendNotification(CurrenciesApplication.getAppContext(),
                            currencyNameAndRateValue.getCurName(), currencyNameAndRateValue.getRate());
                }
            }

        }

//        for (Map.Entry<Integer, Rate> entry : ratesMap.entrySet()) {
//            CurrencyAndRate currencyAndRate = new CurrencyAndRate(currenciesMap.get(entry.getKey()),
//                    entry.getValue().getCurOfficialRate());
//            for ( int id : ids ) {
//                if (id == currencyAndRate.getId()){
//                    for ( Double favoriteRate : favoriteRatesList ) {
//                        if ( currencyAndRate.getRate() < favoriteRate ){
//                            pushNotificationService.sendNotification(context,
//                                    currencyAndRate.getCurrency().getCurAbbreviation(), favoriteRate);
//
//                        }
//                    }
//
//                }
//            }
//        }


    }

    private void save (Map<Integer, Currency> currenciesMap, Map<Integer, Rate> ratesMap) {

        List<CurrencyAndRate> currenciesAndRates = new ArrayList<>();
        CurrentLocalizationIdProvider currentLocalizationIdProvider =
                new CurrentLocalizationIdProvider(CurrenciesApplication.getAppContext());
        String languageId = currentLocalizationIdProvider.provideCurrentLocalizationId();


        for (Map.Entry<Integer, Rate> entry : ratesMap.entrySet()) {
            CurrencyAndRate currencyAndRate = new CurrencyAndRate(currenciesMap.get(entry.getKey()),
                    entry.getValue().getCurOfficialRate());
            currenciesAndRates.add(currencyAndRate);


            CurrencyNameAndRateValue currencyNameAndRateValue = null;
            switch (languageId) {
                case RUS:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurName(),
                            currencyAndRate.getRate());
                    break;
                case BEL:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurNameBel(),
                            currencyAndRate.getRate());
                    break;
                case ENG:
                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurNameEng(),
                            currencyAndRate.getRate());
                    break;
            }

            currencyNameAndRateValue.setId(currencyAndRate.getId());
            currencyDao.save(currencyNameAndRateValue);


        }
    }


}
