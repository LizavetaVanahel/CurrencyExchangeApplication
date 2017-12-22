package com.example.vanahel.currencyexchangeapplication.common.asynctaskloader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.fragments.favoritecur.FavoriteRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskLoaderCallbacks implements LoaderManager.LoaderCallbacks<AsyncTaskResult<List<CurrencyNameAndRateValue>>> {

    private Activity activity;
    private CurrencyDao currencyDao;
    private FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter;

    public AsyncTaskLoaderCallbacks( Activity activity, CurrencyDao currencyDao,
                                    FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter ) {
        this.activity = activity;
        this.currencyDao = currencyDao;
        this.favoriteRecyclerViewAdapter = favoriteRecyclerViewAdapter;
    }


    @Override
    public Loader<AsyncTaskResult<List<CurrencyNameAndRateValue>>> onCreateLoader( int i, Bundle bundle ) {
        return new CurrencyAsyncTaskLoader( activity, currencyDao );
    }

    @Override
    public void onLoadFinished( Loader<AsyncTaskResult<List<CurrencyNameAndRateValue>>> loader,
                               AsyncTaskResult<List<CurrencyNameAndRateValue>> wrapper ) {
        if ( wrapper.getError() == null ) {
            favoriteRecyclerViewAdapter.setTask( wrapper.getResult() );
        }
    }

    @Override
    public void onLoaderReset( Loader<AsyncTaskResult<List<CurrencyNameAndRateValue>>> loader ) {
        favoriteRecyclerViewAdapter.setTask( new ArrayList<CurrencyNameAndRateValue>() );
    }


}
