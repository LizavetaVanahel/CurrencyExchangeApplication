package com.example.vanahel.currencyexchangeapplication.common.asynctaskloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;

import java.util.List;

public class CurrencyAsyncTaskLoader extends AsyncTaskLoader<AsyncTaskResult<List<CurrencyNameAndRateValue>>> {

    private CurrencyDao currencyDao;

    public CurrencyAsyncTaskLoader(Context context, CurrencyDao currencyDao) {
        super(context);
        this.currencyDao = currencyDao;
    }


    @Override
    public AsyncTaskResult<List<CurrencyNameAndRateValue>> loadInBackground() {
        AsyncTaskResult<List<CurrencyNameAndRateValue>> resultWrapper = new AsyncTaskResult<>();
        try {
            resultWrapper.setResult(currencyDao.getCurrenciesAndRates());
        } catch (RuntimeException error) {
            resultWrapper.setError(error);
        }

        return resultWrapper;
    }

}


