package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRateListDTO;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class FavoriteCurrenciesUpdateService {

    private CurrencyDao currencyDao = DaoManager.getInstance().getCurrencyDao();
    private NBRBService service;

    public FavoriteCurrenciesUpdateService (){
        service = CurrenciesApplication.getApi();
    }

    public void getFavoriteCurrencyRate (){

        Observable<List<Currency>> currenciesList = getCurrenciesList();
        Observable<List<Rate>> ratesList = getRates();
        final List<Integer> favoriteCurrenciesId = currencyDao.getFavoriteCurrencyIds();

        final Observable<CurrencyAndRateListDTO> combined = Observable.zip(currenciesList, ratesList,
                new BiFunction<List<Currency>, List<Rate>, CurrencyAndRateListDTO>() {

                    @Override
                    public CurrencyAndRateListDTO apply(@NonNull List<Currency> currencyList,
                                                        @NonNull List<Rate> rateList) throws Exception {
                        return new CurrencyAndRateListDTO(rateList, currencyList);
                    }
                });


        combined.subscribe(new Observer<CurrencyAndRateListDTO>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull CurrencyAndRateListDTO currencyAndRateListDTO) {
                Map<Integer, Currency> currenciesMap = currencyAndRateListDTO.getCurrencyMap();
                Map<Integer, Rate> ratesMap = currencyAndRateListDTO.getRatesMap();
                CurrencyAndRate currencyAndRate = null;
                CurrencyListDisplayer currencyListDisplayer =
                        new CurrencyListDisplayer( CurrenciesApplication.getAppContext() );

                List<CurrencyNameAndRateValue> favoriteCurrencyAndRate = new ArrayList<>();

                    for ( int favoriteId : favoriteCurrenciesId ) {
                        currencyAndRate = new CurrencyAndRate(currenciesMap.get(favoriteId),
                                ratesMap.get(favoriteId).getCurOfficialRate());

                        CurrencyNameAndRateValue currencyNameAndRateValue =
                                currencyListDisplayer.showCurrencyAndRate(currencyAndRate);

                        currencyNameAndRateValue.setId(currencyAndRate.getId());
                        favoriteCurrencyAndRate.add(currencyNameAndRateValue);
                    }

                FavoriteCurrencyUpdateFLow favoriteCurrencyUpdateFLow = new FavoriteCurrencyUpdateFLow();
                favoriteCurrencyUpdateFLow.runUpdateFlow(favoriteCurrencyAndRate);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private Observable<List<Rate>> getRates (){
        return service.getRatesForToday()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Currency>> getCurrenciesList () {
        return service.getCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
