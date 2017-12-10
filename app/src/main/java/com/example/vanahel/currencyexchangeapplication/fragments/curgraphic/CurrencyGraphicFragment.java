package com.example.vanahel.currencyexchangeapplication.fragments.curgraphic;

import android.R.layout;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vanahel.currencyexchangeapplication.R;
import com.example.vanahel.currencyexchangeapplication.R.array;
import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyAndRate;
import com.example.vanahel.currencyexchangeapplication.common.view.CurrencyListView;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListDisplayer;
import com.example.vanahel.currencyexchangeapplication.util.currencylist.CurrencyListProvider;
import com.example.vanahel.currencyexchangeapplication.util.graphic.GraphicDrawer;
import com.example.vanahel.currencyexchangeapplication.util.pushnotservice.PushNotificationService;
import com.github.mikephil.charting.charts.CombinedChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyGraphicFragment extends Fragment implements CurrencyGraphicView, CurrencyListView {

    @BindView(id.combinedChart)
    CombinedChart chart;
    @BindView(id.currencies_for_graphic_spinner)
    Spinner currenciesSpinner;
    @BindView(id.graphic_period)
    Spinner periodSpinner;

    private GraphicDrawer graphicDrawer;
    private List<Integer> currencyIds;
    private int curId;
    private String[] periods;
    private int periodForGraphic;
    private CurrencyListDisplayer currencyListDisplayer;
    private CurrencyGraphicPresenter currencyGraphicPresenter;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String titleContent = intent.getStringExtra("title");
            String descriptionContent = intent.getStringExtra("description");

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_graphics_fragment, container, false);
        ButterKnife.bind(this, view);

        currencyListDisplayer = new CurrencyListDisplayer(getActivity());

        CurrencyListProvider currencyListProvider = new CurrencyListProvider(this);
        currencyListProvider.getCurrencies();

        currencyGraphicPresenter = new CurrencyGraphicPresenter(this);

        periods = getResources().getStringArray(array.graphicPeriod);
        ArrayAdapter<String> spinnerPeriodAdapter =
                new ArrayAdapter<>(getActivity(), layout.simple_spinner_dropdown_item,
                        periods);
        periodSpinner.setAdapter(spinnerPeriodAdapter);
        spinnerPeriodAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);

        periodSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedPeriod = periods[position];
                if (selectedPeriod.contains("3")) {
                    periodForGraphic = 3;
                } else if (selectedPeriod.contains("6")) {
                    periodForGraphic = 6;
                } else if (selectedPeriod.contains("12")) {
                    periodForGraphic = 12;
                }

                currencyGraphicPresenter.getCurrency(curId, periodForGraphic);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        graphicDrawer = new GraphicDrawer(chart);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().getIntent().hasExtra("brodcastreceiver")) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,
                    new IntentFilter(PushNotificationService.INTENT_FILTER));
            broadcastReceiver.onReceive(getActivity(), getActivity().getIntent());
        }
    }

    @Override
    public void showGraphic(Map<Integer, Float> rateDynamics) {
        graphicDrawer.drawGraphic(rateDynamics);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showCurrencyAndRate(List<CurrencyAndRate> currenciesAndRates) {

        List<String> currencyNameList = currencyListDisplayer.showCurrencyList(currenciesAndRates);

        currencyIds = new ArrayList<>();

        for ( CurrencyAndRate currencyAndRate : currenciesAndRates ) {
            currencyIds.add(currencyAndRate.getCurrency().getCurID());
        }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    layout.simple_spinner_item, currencyNameList);
            adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
            currenciesSpinner.setAdapter(adapter);
            currenciesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    curId = currencyIds.get(position);
                    currencyGraphicPresenter.getCurrency(curId, periodForGraphic);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }

