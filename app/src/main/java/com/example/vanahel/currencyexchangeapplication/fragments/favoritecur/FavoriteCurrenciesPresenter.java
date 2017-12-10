package com.example.vanahel.currencyexchangeapplication.fragments.favoritecur;


import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class FavoriteCurrenciesPresenter {

    private final FavoriteCurrenciesView favoriteCurrenciesView;

    public FavoriteCurrenciesPresenter (FavoriteCurrenciesView favoriteCurrenciesView){
        this.favoriteCurrenciesView = favoriteCurrenciesView;
    }

    public void getFavoriteCurrencyRate (Integer currencyId){


        Observable<Rate> rate = getRate(currencyId);

        Observable<Currency> currency = getCurrency(currencyId);

        Observable<CurrencyAndRate> combined = Observable.zip(currency, rate,
                new BiFunction<Currency, Rate,  CurrencyAndRate>() {
                    @Override
                    public CurrencyAndRate apply(@NonNull Currency currency,
                                                 @NonNull Rate rate) throws Exception {

                        return new CurrencyAndRate(currency, rate.getCurOfficialRate());
                    }
                });

        combined.subscribe(new Observer<CurrencyAndRate>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull CurrencyAndRate currencyAndRate) {
                favoriteCurrenciesView.showSelectedCurrencyAndRate(currencyAndRate);

            }
            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private Observable<Rate> getRate (Integer rateId){

        NBRBService service = CurrenciesApplication.getApi();
        return service.getRate(rateId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Currency> getCurrency (Integer rateId){

        NBRBService service = CurrenciesApplication.getApi();
        return service.getCurrency(rateId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
