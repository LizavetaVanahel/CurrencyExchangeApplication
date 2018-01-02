package com.example.vanahel.currencyexchangeapplication.fragments.favoritecur;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.vanahel.currencyexchangeapplication.R;
import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.common.asynctaskloader.AsyncTaskLoaderCallbacks;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.common.view.CurrencyListView;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteCurrenciesFragment extends Fragment implements FavoriteCurrenciesView, CurrencyListView {

    @BindView(id.home_recycler_view)
    RecyclerView recyclerView;

    private  ArrayAdapter<String> arrayAdapter;
    private List<Integer> currencyIdList;
    private int curId;
    private FavoriteCurrenciesPresenter favoriteCurrenciesPresenter;
    private CurrencyDao currencyDao;
    private AsyncTaskLoaderCallbacks asyncTaskLoaderCallbacks;
    private CurrencyListDisplayer currencyListDisplayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.favorite_currencies_fragment, container, false );
        ButterKnife.bind(this, view);

        setRetainInstance(true);
        setHasOptionsMenu(true);

        CurrencyListProvider currencyListProvider = new CurrencyListProvider(this);
        currencyListProvider.provideCurrencyList();

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        favoriteCurrenciesPresenter = new FavoriteCurrenciesPresenter(this);

        LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        currencyDao = DaoManager.getInstance().getCurrencyDao();

        List<CurrencyNameAndRateValue> currencies = currencyDao.getCurrenciesAndRates();

        FavoriteRecyclerViewAdapter adapter = new FavoriteRecyclerViewAdapter(currencies);
        recyclerView.setAdapter(adapter);

        asyncTaskLoaderCallbacks = new AsyncTaskLoaderCallbacks(getActivity(),
                currencyDao, adapter);

        return view;

    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater ) {
        inflater.inflate( R.menu.favorite_menu, menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_new_content_facebook){

            Builder builderSingle = new Builder(getActivity());
            builderSingle.setAdapter( arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    curId = currencyIdList.get(which);
                    favoriteCurrenciesPresenter.getFavoriteCurrencyRate(curId);

                }
            });
            builderSingle.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSelectedCurrencyAndRate( CurrencyAndRate currencyAndRate ) {

        getLoaderManager().restartLoader( 0, null, asyncTaskLoaderCallbacks ).forceLoad();

        CurrencyNameAndRateValue currencyNameAndRateValue =
                currencyListDisplayer.showCurrencyAndRate(currencyAndRate);

        currencyNameAndRateValue.setId( currencyAndRate.getId() );
        currencyDao.save(currencyNameAndRateValue);

    }

    @Override
    public void showCurrencyAndRate( List<CurrencyAndRate> currenciesAndRates ) {

        arrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.select_dialog_singlechoice);

        currencyIdList = new ArrayList<>();

        List<String> currencyNames = currencyListDisplayer.showCurrencyList(currenciesAndRates);

        for ( String currencyName : currencyNames ) {
            arrayAdapter.add(currencyName);
        }

        for ( CurrencyAndRate currency : currenciesAndRates ) {
            currencyIdList.add( currency.getCurrency().getCurID() );

        }
    }
}
