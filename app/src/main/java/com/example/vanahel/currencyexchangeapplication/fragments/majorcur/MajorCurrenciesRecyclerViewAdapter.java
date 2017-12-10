package com.example.vanahel.currencyexchangeapplication.fragments.majorcur;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.fragments.majorcur.MajorCurrenciesRecyclerViewAdapter.ViewHolder;

import java.util.List;

public class MajorCurrenciesRecyclerViewAdapter extends
        Adapter<ViewHolder> {

    private final List<CurrencyNameAndRateValue> currencyNameAndRateValueList;

    public MajorCurrenciesRecyclerViewAdapter(List<CurrencyNameAndRateValue> currencyNameAndRateValueList) {
        this.currencyNameAndRateValueList = currencyNameAndRateValueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        CurrencyNameAndRateValue currencyNameAndRateValue = this.currencyNameAndRateValueList.get(i);

            viewHolder.name.setText(currencyNameAndRateValue.getCurName());
            viewHolder.rate.setText(currencyNameAndRateValue.getRate().toString());
    }


    @Override
    public int getItemCount() {
        return this.currencyNameAndRateValueList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView rate;

        private ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(id.entity_name);
            this.rate = itemView.findViewById(id.entity_rate);

        }
    }
}