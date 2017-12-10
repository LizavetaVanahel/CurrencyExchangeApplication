package com.example.vanahel.currencyexchangeapplication.fragments.curcalculator;

import android.R.layout;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanahel.currencyexchangeapplication.R;
import com.example.vanahel.currencyexchangeapplication.R.drawable;
import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.view.CurrencyListView;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyCalculatorFragment extends Fragment implements CurrencyCalculatorView, CurrencyListView {

    @BindView(id.spinner_to)
    Spinner spinnerTo;
    @BindView(id.result_tv)
    TextView resultTextView;
    @BindView(id.value_to_exchange)
    EditText editText;
    @BindView(id.exchange_direction_button)
    Button exchangeButton;

    private String currencyToAbb;
    private CurrencyCalculatorPresenter currencyCalculatorPresenter;
    private int status;
    private String valueToExchange;
    private List<String> currencyAbbs;
    private CurrencyListDisplayer currencyListDisplayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_calculator_fragment, container, false);
        ButterKnife.bind(this, view);

        CurrencyListProvider currencyListProvider = new CurrencyListProvider(this);
        currencyListProvider.getCurrencies();

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        currencyCalculatorPresenter = new CurrencyCalculatorPresenter(this);

        if (resultTextView.getText() != null) {
            currencyCalculatorPresenter.getRateByAbb( currencyToAbb, valueToExchange, status );
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.spinnerTo.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyToAbb = currencyAbbs.get(position);
                if (!editText.getText().toString().isEmpty()) {
                    valueToExchange = editText.getText().toString();
                    currencyCalculatorPresenter.getRateByAbb(currencyToAbb, valueToExchange, status);
                } else {
                    Toast.makeText(getActivity(), "please, enter value to exchange", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exchangeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 0) {
                    exchangeButton.setCompoundDrawablesWithIntrinsicBounds(drawable.icon_arrow_left, 0, 0, 0);
                    currencyCalculatorPresenter.getRateByAbb(currencyToAbb, valueToExchange, status);
                    status = 1;
                } else {
                    exchangeButton.setCompoundDrawablesWithIntrinsicBounds(drawable.icon_arrow_right, 0, 0, 0);
                    currencyCalculatorPresenter.getRateByAbb(currencyToAbb, valueToExchange, status);
                    status = 0;
                }
            }

        });

    }


    @Override
    public void showCalculatedRate(Double result) {
        resultTextView.setText(result.toString());
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showCurrencyAndRate(List<CurrencyAndRate> currenciesAndRates) {

        List<String> currencyNameList = currencyListDisplayer.showCurrencyList(currenciesAndRates);

        currencyAbbs = new ArrayList<>();

        for ( CurrencyAndRate currencyAndRate : currenciesAndRates ) {
            currencyAbbs.add(currencyAndRate.getCurrency().getCurAbbreviation());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                layout.simple_spinner_item, currencyNameList);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapter);
    }
}
