package com.example.vanahel.currencyexchangeapplication.util.metal;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Ingot;
import com.example.vanahel.currencyexchangeapplication.common.model.entities.metals.Metal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetalDataCombiner {

    public Map<String, Double> combine (List<Metal> metalsList, List<Ingot> ingotsList){

        Map<String, Double> metalsRate = new HashMap<>();

        Map<Integer, String> metals = this.provideMetalsMap(metalsList);

        for ( Ingot ingot : ingotsList ) {
           String metalName = metals.get(ingot.getMetalID());
            metalsRate.put(metalName, ingot.getBanksRubles());
        }

        return metalsRate;

    }

    private Map<Integer, String> provideMetalsMap (List<Metal> metalsList){

        Map<Integer, String> metals = new HashMap<>();

        for ( Metal metal : metalsList ) {
            metals.put(metal.getId(), metal.getName());
        }

        return metals;
    }

}
