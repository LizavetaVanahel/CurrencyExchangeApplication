package com.example.vanahel.currencyexchangeapplication.fragments.metalsrate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.MetalAndIngot;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.MetalNameAndIngotValue;
import com.example.vanahel.currencyexchangeapplication.util.localization.CurrentLocalizationIdProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.BEL;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.ENG;
import static com.example.vanahel.currencyexchangeapplication.languages.LanguageConstants.RUS;

public class IngotsRateFragment extends Fragment implements IngotsRateView{

    @BindView(id.ingots_recycler_view)
    RecyclerView recyclerView;

    private String languageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.metals_rate_fragment, container, false);
        setRetainInstance(true);
         ButterKnife.bind(this, view);

            LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            IngotsRatePresenter ingotsRatePresenter =  new IngotsRatePresenter(this);
            ingotsRatePresenter.getMetalsAndIngots();

            CurrentLocalizationIdProvider currentLocalizationIdProvider = new CurrentLocalizationIdProvider(this.getActivity());
            languageId =  currentLocalizationIdProvider.provideCurrentLocalizationId();

        return view;

    }

    @Override
    public void showMetalsRate( List<MetalAndIngot> metalsAndIngots) {

        MetalNameAndIngotValue metalNameAndIngotValue;
        List<MetalNameAndIngotValue> metalNameAndIngotValueList = new ArrayList<>();


        for (MetalAndIngot metalAndIngot : metalsAndIngots) {
            switch (languageId) {
                case RUS:
                    metalNameAndIngotValue = new MetalNameAndIngotValue(metalAndIngot.getMetal().getName(),
                            metalAndIngot.getNominal(), metalAndIngot.getPrice());
                    metalNameAndIngotValueList.add(metalNameAndIngotValue);
                    break;
                case BEL:
                    metalNameAndIngotValue = new MetalNameAndIngotValue(metalAndIngot.getMetal().getNameBel(),
                            metalAndIngot.getNominal(), metalAndIngot.getPrice());
                    metalNameAndIngotValueList.add(metalNameAndIngotValue);
                    break;
                case ENG:
                    metalNameAndIngotValue = new MetalNameAndIngotValue(metalAndIngot.getMetal().getNameEng(),
                            metalAndIngot.getNominal(), metalAndIngot.getPrice());
                    metalNameAndIngotValueList.add(metalNameAndIngotValue);
                    break;

            }

            Adapter adapter = new IngotsRecyclerViewAdapter(metalNameAndIngotValueList);
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void showError(String error) {

    }
}
