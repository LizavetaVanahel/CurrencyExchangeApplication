package com.example.vanahel.currencyexchangeapplication.fragments.favoritecur;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;
import com.example.vanahel.currencyexchangeapplication.dao.CurrencyDao;
import com.example.vanahel.currencyexchangeapplication.dao.DaoManager;
import com.example.vanahel.currencyexchangeapplication.fragments.favoritecur.FavoriteRecyclerViewAdapter.ViewHolder;

import java.util.List;


public class FavoriteRecyclerViewAdapter extends Adapter<ViewHolder> {

    private final List<CurrencyNameAndRateValue> currencyNameAndRateValueList;
    private CurrencyDao currencyDao;


    public FavoriteRecyclerViewAdapter(List<CurrencyNameAndRateValue> currencyNameAndRateValueList) {
        this.currencyNameAndRateValueList = currencyNameAndRateValueList;
        currencyDao = DaoManager.getInstance().getCurrencyDao();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        CurrencyNameAndRateValue currencyNameAndRateValue = currencyNameAndRateValueList.get(i);

        viewHolder.name.setText(currencyNameAndRateValue.getCurName());
        viewHolder.rate.setText(currencyNameAndRateValue.getRate().toString());

    }

    @Override
    public int getItemCount() {
        return currencyNameAndRateValueList.size();
    }

    public void setTask(List<CurrencyNameAndRateValue> data) {
        currencyNameAndRateValueList.clear();
        currencyNameAndRateValueList.addAll(data);
        notifyDataSetChanged();
    }


    private void delete(int position) {
        CurrencyNameAndRateValue currencyNameAndRateValue = currencyNameAndRateValueList.get(position);
        currencyDao.delete(currencyNameAndRateValue);
        currencyNameAndRateValueList.clear();
        currencyNameAndRateValueList.addAll(currencyDao.getCurrenciesAndRates());
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private final TextView name;
        private final TextView rate;

        private ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(id.entity_name);
            rate = (TextView) itemView.findViewById(id.entity_rate);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            delete(getAdapterPosition());

        }


    }
}