package com.example.vanahel.currencyexchangeapplication.common.network;


import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Ingot;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Metal;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Rate;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NBRBService {

        @GET("API/ExRates/Currencies/{Cur_ID}")
        Observable<Currency> getCurrency(@Path("Cur_ID") int currencyId);

        @GET("API/ExRates/Currencies")
        Observable<List<Currency>>getCurrencies();

        @GET("API/ExRates/Rates/{Cur_ID}")
        Observable<Rate>getRate(@Path("Cur_ID") int rateId);

        @GET("API/Ingots/Prices")
        Observable<List<Ingot>> getIngots(@Query("onDate") String date);

        @GET("API/ExRates/Rates?Periodicity=0")
        Observable<List<Rate>> getRatesForToday();

        @GET("API/ExRates/Rates/Dynamics/{Cur_ID}")
        Observable<List<Rate>>getRateDynamic(@Path("Cur_ID") int rateId, @Query("startDate") String startDate,
                                             @Query("endDate") String endDate);

        @GET("/API/ExRates/Rates/{Cur_Abbreviation}?ParamMode=2")
        Observable<Rate> getRateForParticularDateByAbbreviation(@Path("Cur_Abbreviation") String curAbbreviation,
                                                                @Query("onDate") String date);

        @GET("/API/ExRates/Rates/{Cur_Abbreviation}?ParamMode=2")
        Observable<Rate>getRateByAbbreviation(@Path("Cur_Abbreviation") String curAbbreviation);

        @GET("API/Metals")
        Observable<List<Metal>>getMetals();

}
