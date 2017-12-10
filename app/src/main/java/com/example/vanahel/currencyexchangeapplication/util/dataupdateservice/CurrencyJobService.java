package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Liza Kaliada on 30.11.17.
 */

public class CurrencyJobService extends JobService {

    private static final String TAG = CurrencyJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters job) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                FavoriteCurrenciesUpdateService favoriteCurrenciesUpdateService =
                        new FavoriteCurrenciesUpdateService();
                favoriteCurrenciesUpdateService.getFavoriteCurrencyRate();
            }
        }).start();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
