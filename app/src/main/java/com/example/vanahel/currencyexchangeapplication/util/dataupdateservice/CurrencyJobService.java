package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Liza Kaliada on 12.12.17.
 */

public class CurrencyJobService extends JobService {

    private static final String TAG = CurrencyJobService.class.getSimpleName();
    private FavoriteCurrenciesUpdateService favoriteCurrenciesUpdateService;

    @Override
    public boolean onStartJob(final JobParameters params) {
        favoriteCurrenciesUpdateService = new FavoriteCurrenciesUpdateService();
        favoriteCurrenciesUpdateService.getFavoriteCurrencyRate();
        //Offloading work to a new thread.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                codeYouWantToRun(params);
//            }
//        }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public void codeYouWantToRun(final JobParameters parameters) {
        try {

            Log.d(TAG, "completeJob: " + "jobStarted");
            //This task takes 2 seconds to complete.
            Thread.sleep(2000);

            Log.d(TAG, "completeJob: " + "jobFinished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Tell the framework that the job has completed and doesnot needs to be reschedule
            jobFinished(parameters, true);
        }
    }
}
