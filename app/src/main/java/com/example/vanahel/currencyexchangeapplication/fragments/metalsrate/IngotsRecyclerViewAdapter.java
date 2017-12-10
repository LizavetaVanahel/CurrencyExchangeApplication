package com.example.vanahel.currencyexchangeapplication.fragments.metalsrate;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.MetalNameAndIngotValue;
import com.example.vanahel.currencyexchangeapplication.fragments.metalsrate.IngotsRecyclerViewAdapter.ViewHolder;

import java.util.List;

public class IngotsRecyclerViewAdapter extends Adapter<ViewHolder> {

    private final List<MetalNameAndIngotValue> metalNameAndIngotValueList;

    public IngotsRecyclerViewAdapter(List<MetalNameAndIngotValue> metalNameAndIngotValueList) {
        this.metalNameAndIngotValueList = metalNameAndIngotValueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout.metals_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MetalNameAndIngotValue metalNameAndIngotValue = metalNameAndIngotValueList.get(i);

        viewHolder.name.setText(metalNameAndIngotValue.getMetalName());
        viewHolder.nominal.setText(metalNameAndIngotValue.getNominal().toString());
        viewHolder.rate.setText(metalNameAndIngotValue.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return this.metalNameAndIngotValueList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView rate;
        private final TextView nominal;

        private ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(id.metal_name);
            this.rate = itemView.findViewById(id.metal_rate);
            this.nominal = itemView.findViewById(id.metal_scale);
        }
    }
}