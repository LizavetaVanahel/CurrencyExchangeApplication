package com.example.vanahel.currencyexchangeapplication.fragments.majorcur;

import android.text.format.DateFormat;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRateListDTO;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class MajorCurrencyRatePresenter {

    private final MajorCurrencyRateView majorCurrencyRateView;

    public MajorCurrencyRatePresenter (MajorCurrencyRateView majorCurrencyRateView){
        this.majorCurrencyRateView = majorCurrencyRateView;

    }

    public void getMajorCurrencyAndRate (final Set<Integer> majorCurrencyIds){


        Observable<List<Rate>> ratesList = this.getRates();

        Observable<List<Currency>> currenciesList = getCurrencies();

        Observable<CurrencyAndRateListDTO> combined = Observable.zip(ratesList, currenciesList,
                new BiFunction<List<Rate>, List<Currency>, CurrencyAndRateListDTO>() {
                    @Override
                    public CurrencyAndRateListDTO apply(@NonNull List<Rate> rates,
                                                        @NonNull List<Currency> currencyList) throws Exception {
                        return new CurrencyAndRateListDTO(rates, currencyList);
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

                List<CurrencyAndRate> currenciesAndRates = new ArrayList<>();

                for ( Integer majorCurrencyId : majorCurrencyIds ) {
                    CurrencyAndRate currencyAndRate = new CurrencyAndRate(currenciesMap.get(majorCurrencyId),
                            ratesMap.get(majorCurrencyId).getCurOfficialRate());
                    currenciesAndRates.add(currencyAndRate);
                }

                majorCurrencyRateView.showCurrencyAndRate(currenciesAndRates);

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

        NBRBService service = CurrenciesApplication.getApi();
        return service.getRatesForToday()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void provideCurrentDate (String languageId){
        Date d = new Date();
        CharSequence currentDate = null;
            if (languageId.equals("en")){
                currentDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
            } else if (languageId.equals("ru") || languageId.equals("be")){
                currentDate  = DateFormat.format("d MMMM, yyyy ", d.getTime());
            }
        this.majorCurrencyRateView.showCurrentDate(currentDate);

    }

    public Observable<List<Currency>> getCurrencies() {
        NBRBService service = CurrenciesApplication.getApi();

        return service.getCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
