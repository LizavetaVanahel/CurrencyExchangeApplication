package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Liza Kaliada on 12.12.17.
 */

public class CurrencyJobService extends JobService {

    private FavoriteCurrenciesUpdateService favoriteCurrenciesUpdateService;

    @Override
    public boolean onStartJob( final JobParameters params ) {
        favoriteCurrenciesUpdateService = new FavoriteCurrenciesUpdateService();
        favoriteCurrenciesUpdateService.getFavoriteCurrencyRate();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

}
