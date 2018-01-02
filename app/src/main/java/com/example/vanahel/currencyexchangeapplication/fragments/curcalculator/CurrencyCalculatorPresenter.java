package com.example.vanahel.currencyexchangeapplication.fragments.curcalculator;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrencyCalculatorPresenter {

    private final CurrencyCalculatorView currencyCalculatorView;

    public CurrencyCalculatorPresenter ( CurrencyCalculatorView currencyCalculatorView ){
        this.currencyCalculatorView = currencyCalculatorView;
    }

    public void getRateByAbb( String curAbb, final String valueToExchange, final int status ){

        NBRBService service = CurrenciesApplication.getApi();
        service.getRateByAbbreviation(curAbb)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rate>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Rate rate) {
                        double doubleValueToExchange = Double.parseDouble(valueToExchange);
                        double calculatedResult;
                        if (status == 0){
                            calculatedResult  = calculateFromBel( rate.getCurOfficialRate(),
                                    doubleValueToExchange );
                        } else {
                            calculatedResult = calculateToBel( rate.getCurOfficialRate(),
                                    doubleValueToExchange );
                        }
                        currencyCalculatorView.showCalculatedRate(calculatedResult);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private double calculateFromBel ( double currencyToRate, double valueToExchange ){
        return currencyToRate * valueToExchange;
    }

    private double calculateToBel( double currencyToRate, double valueToExchange ){
        return currencyToRate / valueToExchange;
    }

}
