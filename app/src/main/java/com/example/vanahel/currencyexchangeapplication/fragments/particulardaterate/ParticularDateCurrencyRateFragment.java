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
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.Currency;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vanahel.currencyexchangeapplication.constants.language.LanguageConstants.BEL;
import static com.example.vanahel.currencyexchangeapplication.constants.language.LanguageConstants.ENG;
import static com.example.vanahel.currencyexchangeapplication.constants.language.LanguageConstants.RUS;

public class ParticularDateCurrencyRateFragment extends Fragment
        implements ParticularDateCurrencyRateView{

    @BindView(id.calendarView)
    CalendarView calendarView;
    @BindView(id.particular_date_rate)
    TextView rateDisplay;
    @BindView(id.spinner)
    Spinner spinner;

    private String date;
    private String currencyAbbreviation;
    private String languageId;
    private List<String> currencyAbbreviationList;
    private CurrencyListDisplayer currencyListDisplayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.particular_date_currency_fragment, container, false);
        setRetainInstance(true);
        ButterKnife.bind(this, view);

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        final ParticularDateCurrencyRatePresenter particularDateCurrencyRatePresenter =
                new ParticularDateCurrencyRatePresenter(this);

        particularDateCurrencyRatePresenter.getCurrenciesList();

        calendarView.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
                particularDateCurrencyRatePresenter.getCurrenciesForParticularDate(currencyAbbreviation, date);

            }

    });

        this.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyAbbreviation = currencyAbbreviationList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CurrentLocalizationIdProvider currentLocalizationIdProvider =
                new CurrentLocalizationIdProvider(this.getActivity());
        this.languageId = currentLocalizationIdProvider.provideCurrentLocalizationId();

        return view;

    }


    @Override
    public void setCurrenciesList(List<Currency> currenciesList){


        List<String> currencyNameList = new ArrayList<>();
        currencyAbbreviationList = new ArrayList<>();
        for ( Currency currency : currenciesList ) {
            switch (languageId) {
                case RUS:
                    currencyNameList.add(currency.getCurName());
                    break;
                case BEL:
                    currencyNameList.add(currency.getCurNameBel());
                    break;
                case ENG:
                    currencyNameList.add(currency.getCurNameEng());
                    break;

            }

            currencyAbbreviationList.add(currency.getCurAbbreviation());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                layout.simple_spinner_item, currencyNameList);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void showParticularDateRate(Double rate) {
        rateDisplay.setText(rate.toString());
    }

    @Override
    public void showError (String error){

    }
}
