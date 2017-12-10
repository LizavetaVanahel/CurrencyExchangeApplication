package com.example.vanahel.currencyexchangeapplication.fragments.particulardaterate;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ParticularDateCurrencyRatePresenter {

    private final ParticularDateCurrencyRateView particularDateCurrencyRateView;
    private final NBRBService service;

    public ParticularDateCurrencyRatePresenter(ParticularDateCurrencyRateView particularDateCurrencyRateView){
        this.particularDateCurrencyRateView = particularDateCurrencyRateView;
        service = CurrenciesApplication.getApi();

    }

    public void getCurrenciesList () {
        NBRBService service = CurrenciesApplication.getApi();

         service.getCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Currency>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Currency> currencyList) {
                        particularDateCurrencyRateView.setCurrenciesList(currencyList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

        public void getCurrenciesForParticularDate(String curAbbrevation, String date){

            service.getRateForParticularDateByAbbreviation(curAbbrevation, date)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Rate>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Rate rate) {
                            particularDateCurrencyRateView.showParticularDateRate(rate.getCurOfficialRate());

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
}

