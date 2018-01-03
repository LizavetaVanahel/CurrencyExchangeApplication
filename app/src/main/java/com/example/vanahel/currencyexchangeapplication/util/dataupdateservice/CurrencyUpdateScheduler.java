package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Liza Kaliada on 12.12.17.
 */

public class CurrencyUpdateScheduler  {

    public static void scheduleJob(Context context) {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = createJob(dispatcher);
        dispatcher.mustSchedule(job);
    }

    public static Job createJob(FirebaseJobDispatcher dispatcher){

        ExecutionWindowCalculator executionWindowCalculator = new ExecutionWindowCalculator();
        int updateTime = executionWindowCalculator.calculateExecutionWindow(12);

        Job job = dispatcher.newJobBuilder()
                .setLifetime(Lifetime.FOREVER)
                .setService(CurrencyJobService.class)
                .setTag("CurrencyAppTag")
                .setReplaceCurrent(false)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(updateTime, updateTime + 60))
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setConstraints(Constraint.ON_ANY_NETWORK, Constraint.DEVICE_CHARGING)
                .build();
        return job;
    }

    public void cancelJob(Context context){

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        dispatcher.cancelAll();
        dispatcher.cancel("CurrencyAppTag");

    }
}
