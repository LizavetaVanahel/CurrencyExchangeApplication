package com.example.vanahel.currencyexchangeapplication.fragments.majorcur;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;
import com.example.vanahel.currencyexchangeapplication.util.propertyreader.MajorCurrencyIdProvider;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  MajorCurrencyRateFragment extends Fragment implements MajorCurrencyRateView{

    @BindView(id.date)
    TextView date;
    @BindView(id.major_recycler_view)
    RecyclerView recyclerView;

    private String languageId;
    private CurrencyListDisplayer currencyListDisplayer;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( layout.major_currency_rate_fragment, container, false );
        setRetainInstance(true);
        ButterKnife.bind(this, view);

        LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        MajorCurrencyIdProvider majorCurrencyIdProvider = new MajorCurrencyIdProvider();
        Set<Integer> majorCurrencyIds = majorCurrencyIdProvider.provideMajorCurrenciesId(getActivity());

        CurrentLocalizationIdProvider currentLocalizationIdProvider =
                new CurrentLocalizationIdProvider(getActivity());
        languageId =  currentLocalizationIdProvider.provideCurrentLocalizationId();

        MajorCurrencyRatePresenter majorCurrencyRatePresenter = new MajorCurrencyRatePresenter(this);
        majorCurrencyRatePresenter.getMajorCurrencyAndRate(majorCurrencyIds);
        majorCurrencyRatePresenter.provideCurrentDate(languageId);
        return view;
    }

    @Override
    public void showCurrentDate(CharSequence currentDate) {
        date.setText(currentDate);
    }

    @Override
    public void showCurrencyAndRate(List<CurrencyAndRate> currenciesAndRates) {

        List<CurrencyNameAndRateValue> currencyNameAndRateValueList =
                currencyListDisplayer.showCurrencyAndRateList(currenciesAndRates);

        Adapter adapter = new MajorCurrenciesRecyclerViewAdapter(currencyNameAndRateValueList);
        recyclerView.setAdapter(adapter);
    }
}