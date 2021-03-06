package com.example.vanahel.currencyexchangeapplication.fragments.curcalculator;

import android.R.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanahel.currencyexchangeapplication.R;
import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.view.CurrencyListView;
import com.example.vanahel.currencyexchangeapplication.fragments.curcalculator.dto.StatusDTO;
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
    @BindView(id.calculate_button)
    Button calculateButton;

    private String currencyToAbb;
    private CurrencyCalculatorPresenter currencyCalculatorPresenter;
    private String valueToExchange;
    private List<String> currencyAbbs;
    private CurrencyListDisplayer currencyListDisplayer;
    private  final StatusDTO statusDTO = new StatusDTO();

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.currency_calculator_fragment, container, false );
        ButterKnife.bind(this, view);

        CurrencyListProvider currencyListProvider = new CurrencyListProvider(this);
        currencyListProvider.provideCurrencyList();

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        currencyCalculatorPresenter = new CurrencyCalculatorPresenter(this);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    valueToExchange = editText.getText().toString();
                    currencyCalculatorPresenter.getRateByAbb( currencyToAbb, valueToExchange,
                            statusDTO.getStatus() );
                } else {
                    Toast.makeText(getActivity(), "Please, enter value to exchange",
                            Toast.LENGTH_LONG).show();

                }
            }

        });

        spinnerTo.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                currencyToAbb = currencyAbbs.get(position);
                if ( !editText.getText().toString().isEmpty() ) {
                    valueToExchange = editText.getText().toString();
                } else {
                    Toast.makeText(getActivity(), "Please, enter value to exchange",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( statusDTO.getStatus() == 0 ) {
                    exchangeButton.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_arrow_left,
                            0, 0, 0 );
                    currencyCalculatorPresenter.getRateByAbb( currencyToAbb, valueToExchange,
                            statusDTO.getStatus() );
                    statusDTO.setStatus(1);
                } else {
                    exchangeButton.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_arrow_right,
                            0, 0, 0 );
                    currencyCalculatorPresenter.getRateByAbb( currencyToAbb, valueToExchange,
                            statusDTO.getStatus() );
                    statusDTO.setStatus(0);
                }
            }

        });

        return view;
    }

    @Override
    public void showCalculatedRate( Double result ) {
        resultTextView.setText( result.toString() );

    }

    @Override
    public void showCurrencyAndRate( List<CurrencyAndRate> currenciesAndRates ) {

        List<String> currencyNameList = currencyListDisplayer.showCurrencyList(currenciesAndRates);

        currencyAbbs = new ArrayList<>();

        for ( CurrencyAndRate currencyAndRate : currenciesAndRates ) {
            currencyAbbs.add( currencyAndRate.getCurrency().getCurAbbreviation() );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                layout.simple_spinner_item, currencyNameList);
        adapter.setDropDownViewResource( layout.simple_spinner_dropdown_item );
        spinnerTo.setAdapter(adapter);
    }

}
