package com.example.vanahel.currencyexchangeapplication.fragments.curgraphic;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrencyGraphicPresenter {

    private final CurrencyGraphicView currencyGraphicView;

    public CurrencyGraphicPresenter (CurrencyGraphicView currencyGraphicView){
        this.currencyGraphicView = currencyGraphicView;
    }

    public void getCurrency(int rateId, int months){

        String startDate = startDateProvider(months);
        String endDate = endDateProvider();

        NBRBService service = CurrenciesApplication.getApi();
        service.getRateDynamic(rateId, startDate, endDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rate>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Rate> rates) {
                        if (rates.size() == 0){
                            currencyGraphicView.showError("No data for selected currency");
                        } else {
                            Map<Integer, Float> rateDynamics = new TreeMap<>();
                            for ( Rate rate : rates ) {
                                int month = getMonthFromDate(rate.getDate());
                                rateDynamics.put(month, rate.getCurOfficialRate().floatValue());
                            }

                            currencyGraphicView.showGraphic(rateDynamics);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private String startDateProvider (int monthes){

        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d", Locale.US);
        c.add(Calendar.MONTH, -monthes +1);

        return sdf.format(c.getTime());

    }

    private String endDateProvider (){

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d", Locale.US);
        return sdf.format(d.getTime());

    }

    private int getMonthFromDate (String date){

        String str[] = date.split("-");

        return Integer.parseInt(str[1]);
    }

}
