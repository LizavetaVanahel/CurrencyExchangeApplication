package com.example.vanahel.currencyexchangeapplication;

import android.app.Application;

import com.example.vanahel.currencyexchangeapplication.common.network.NBRBService;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.dao.DatabaseDAO;
import com.example.vanahel.currencyexchangeapplication.util.localization.DeviceLocalizationProvider;
import com.example.vanahel.currencyexchangeapplication.util.network.NetworkAvailabilityProvider;
import com.example.vanahel.currencyexchangeapplication.util.dataupdateservice.CurrencyJobCreator;
import com.example.vanahel.currencyexchangeapplication.util.dataupdateservice.FavoriteCurrenciesUpdateService;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrenciesApplication extends Application {

    private static NBRBService nbrbService;
    private NetworkAvailabilityProvider networkAvailabilityProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        DeviceLocalizationProvider deviceLocalizationProvider = new DeviceLocalizationProvider();
        deviceLocalizationProvider.getCurrentLanguage();

        CurrencyJobCreator.scheduleJob(this);
        DaoManager.getInstance().setCurrencyDao(new DatabaseDAO(this));

        networkAvailabilityProvider = new NetworkAvailabilityProvider(this);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .cache(new Cache(getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .addInterceptor(new Interceptor() {
                    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        if (networkAvailabilityProvider.isNetworkAvailable()) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age="
                                    + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, " +
                                    "max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();

        Retrofit retrofit = new Builder()
                .baseUrl("http://www.nbrb.by/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        nbrbService = retrofit.create(NBRBService.class);

        FavoriteCurrenciesUpdateService favoriteCurrenciesUpdateService = new FavoriteCurrenciesUpdateService();
        favoriteCurrenciesUpdateService.getFavoriteCurrencyRate();


    }

    public static NBRBService getApi() {
        return nbrbService;
    }


}
