package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import android.content.Context;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;
import com.example.vanahel.currencyexchangeapplication.util.pushnotservice.PushNotificationService;

import java.util.List;
import java.util.Map;

/**
 * Created by Liza Kaliada on 10.12.17.
 */

public class FavoriteCurrencyUpdateFLow {

    private Context context;
    private CurrencyDao currencyDao = DaoManager.getInstance().getCurrencyDao();


    public void runUpdateFlow (Map<Integer, Currency> currenciesMap, Map<Integer, Rate> ratesMap){

        sendNotification(currenciesMap, ratesMap);
        save(currenciesMap, ratesMap);

    }

    private void sendNotification (Map<Integer, Currency> currenciesMap, Map<Integer, Rate> ratesMap){

        PushNotificationService pushNotificationService = new PushNotificationService();

        List<Integer> ids = currencyDao.getFavoriteCurrencyIds();

        List<Double> favoriteRatesList = currencyDao.getRates();

        for (Map.Entry<Integer, Rate> entry : ratesMap.entrySet()) {
            CurrencyAndRate currencyAndRate = new CurrencyAndRate(currenciesMap.get(entry.getKey()),
                    entry.getValue().getCurOfficialRate());
            for ( int id : ids ) {
                if (id == currencyAndRate.getId()){
                    for ( Double favoriteRate : favoriteRatesList ) {
                        if ( currencyAndRate.getRate() < favoriteRate ){
//                            pushNotificationService.sendNotification(context,
//                                    currencyAndRate.getCurrency().getCurAbbreviation(), favoriteRate);

                        }
                    }

                }
            }
        }


    }

    private void save (Map<Integer, Currency> currenciesMap, Map<Integer, Rate> ratesMap) {

//        List<CurrencyAndRate> currenciesAndRates = new ArrayList<>();
        CurrentLocalizationIdProvider currentLocalizationIdProvider =
                new CurrentLocalizationIdProvider(context);
//        String languageId = currentLocalizationIdProvider.provideCurrentLocalizationId();


//        for (Map.Entry<Integer, Rate> entry : ratesMap.entrySet()) {
//            CurrencyAndRate currencyAndRate = new CurrencyAndRate(currenciesMap.get(entry.getKey()),
//                    entry.getValue().getCurOfficialRate());
////            currenciesAndRates.add(currencyAndRate);
//
//
//            CurrencyNameAndRateValue currencyNameAndRateValue = null;
//            switch (languageId) {
//                case RUS:
//                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurName(),
//                            currencyAndRate.getRate());
//                    break;
//                case BEL:
//                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurNameBel(),
//                            currencyAndRate.getRate());
//                    break;
//                case ENG:
//                    currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyAndRate.getCurrency().getCurNameEng(),
//                            currencyAndRate.getRate());
//                    break;
//            }
//
//            currencyNameAndRateValue.setId(currencyAndRate.getId());
//            currencyDao.save(currencyNameAndRateValue);
//
//
//        }
    }


}
