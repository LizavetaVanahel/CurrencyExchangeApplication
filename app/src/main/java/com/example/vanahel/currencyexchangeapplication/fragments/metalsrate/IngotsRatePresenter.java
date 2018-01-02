package com.example.vanahel.currencyexchangeapplication.fragments.metalsrate;

import android.text.format.DateFormat;

import com.example.vanahel.currencyexchangeapplication.CurrenciesApplication;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Ingot;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Metal;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.MetalAndIngot;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.MetalAndIngotListDTO;
import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class IngotsRatePresenter {

    private final IngotsRateView ingotsRateView;
    private final NBRBService service;

    public IngotsRatePresenter(IngotsRateView ingotsRateView) {
        this.ingotsRateView = ingotsRateView;
        service = CurrenciesApplication.getApi();

    }

    public void getMetalsAndIngots() {

        String currentDate = getCurrentDate();
        Observable<List<Ingot>> ingotsList = getObservableIngotsList(currentDate);
        Observable<List<Metal>> metalsList = getObservableMetalList();
        Observable<MetalAndIngotListDTO> combined = Observable.zip(ingotsList, metalsList,
                new BiFunction<List<Ingot>, List<Metal>, MetalAndIngotListDTO>() {
                    @Override
                    public MetalAndIngotListDTO apply(@NonNull List<Ingot> ingots,
                                                      @NonNull List<Metal> metals) throws Exception {
                        return new MetalAndIngotListDTO(ingots, metals);
                    }
                });

        combined.subscribe(new Observer<MetalAndIngotListDTO>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MetalAndIngotListDTO metalAndIngotListDTO) {
                Map<Integer, Metal> metalsMap = metalAndIngotListDTO.getMetalMap();
                Map<Integer, Ingot> ingotsMap = metalAndIngotListDTO.getIngotMap();

                List<MetalAndIngot> metalsAndIngots = new ArrayList<>();

                for ( Entry<Integer, Ingot> entry : ingotsMap.entrySet() ) {
                    MetalAndIngot metalAndIngot = new MetalAndIngot(metalsMap.get(entry.getKey()),
                            entry.getValue().getNominal(), entry.getValue().getBanksRubles());
                     metalsAndIngots.add(metalAndIngot);
                }

                ingotsRateView.showMetalsRate(metalsAndIngots);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private String getCurrentDate (){
        Date d = new Date();
        return DateFormat.format("yyyy-MM-d ", d.getTime()).toString();
    }

    private Observable<List<Ingot>> getObservableIngotsList (String currentDate){
        return service.getIngots(currentDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Metal>> getObservableMetalList (){
        return service.getMetals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());


    }
}
