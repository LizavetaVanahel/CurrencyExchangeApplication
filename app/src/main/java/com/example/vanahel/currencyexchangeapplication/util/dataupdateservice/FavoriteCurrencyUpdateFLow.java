package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.util.pushnotservice.PushNotificationService;

import java.util.List;

/**
 * Created by Liza Kaliada on 10.12.17.
 */

public class FavoriteCurrencyUpdateFLow {

    private CurrencyDao currencyDao = DaoManager.getInstance().getCurrencyDao();

    public void runUpdateFlow ( List<CurrencyNameAndRateValue> favoriteCurrencyAndRate ){

        sendNotification(favoriteCurrencyAndRate);
        save(favoriteCurrencyAndRate);

    }

    private void sendNotification ( List<CurrencyNameAndRateValue> favoriteCurrencyAndRate ){

        PushNotificationService pushNotificationService = new PushNotificationService();

        List<Double> favoriteRatesBeforeUpdate = currencyDao.getRates();

        for ( CurrencyNameAndRateValue currencyNameAndRateValue : favoriteCurrencyAndRate ) {
            for ( double beforeUpdateRate : favoriteRatesBeforeUpdate ) {
                if ( currencyNameAndRateValue.getRate() < beforeUpdateRate ) {
                    pushNotificationService.sendNotification( CurrenciesApplication.getAppContext(),
                            currencyNameAndRateValue.getCurName(), currencyNameAndRateValue.getRate() );
                }
            }

        }

    }

    private void save ( List<CurrencyNameAndRateValue> favoriteCurrencyAndRate ) {

        currencyDao.deleteAll();

        for ( CurrencyNameAndRateValue currencyNameAndRateValue  : favoriteCurrencyAndRate ) {
            currencyNameAndRateValue.setId( currencyNameAndRateValue.getId() );
            currencyDao.save(currencyNameAndRateValue);
        }
    }

}
