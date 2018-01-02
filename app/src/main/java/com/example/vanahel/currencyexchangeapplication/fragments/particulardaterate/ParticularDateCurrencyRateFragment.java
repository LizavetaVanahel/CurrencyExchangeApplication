package com.example.vanahel.currencyexchangeapplication.fragments.particulardaterate;

import android.R.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vanahel.currencyexchangeapplication.R;
import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.view.CurrencyListView;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticularDateCurrencyRateFragment extends Fragment
        implements ParticularDateCurrencyRateView, CurrencyListView {

    @BindView(id.calendarView)
    CalendarView calendarView;
    @BindView(id.particular_date_rate)
    TextView rateDisplay;
    @BindView(id.spinner)
    Spinner spinner;

    private String date;
    private String currencyAbbreviation;
    private List<String> currencyAbbreviationList;
    private CurrencyListDisplayer currencyListDisplayer;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.particular_date_currency_fragment, container, false );
        setRetainInstance(true);
        ButterKnife.bind(this, view);

        final ParticularDateCurrencyRatePresenter particularDateCurrencyRatePresenter =
                new ParticularDateCurrencyRatePresenter(this);

        currencyListDisplayer = new CurrencyListDisplayer( getActivity() );

        CurrencyListProvider currencyListProvider = new CurrencyListProvider(this);
        currencyListProvider.provideCurrencyList();

        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
                particularDateCurrencyRatePresenter.getCurrenciesForParticularDate( currencyAbbreviation, date );

            }

    });

        spinner.setOnItemSelectedListener( new OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                currencyAbbreviation = currencyAbbreviationList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;

    }

    @Override
    public void showParticularDateRate(double rate) {
        rateDisplay.setText(Double.valueOf(rate).toString());
    }

    @Override
    public void showCurrencyAndRate(List<CurrencyAndRate> currenciesAndRates) {
        currencyAbbreviationList = new ArrayList<>();
        List<String> currencyNameList;

        for ( CurrencyAndRate currencyAndRate : currenciesAndRates ) {
            currencyAbbreviationList.add( currencyAndRate.getCurrency().getCurAbbreviation() );
        }

        currencyNameList = currencyListDisplayer.showCurrencyList(currenciesAndRates);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                layout.simple_spinner_item, currencyNameList);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

