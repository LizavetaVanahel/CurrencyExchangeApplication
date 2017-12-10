package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MetalAndIngotListDTO {

    private final List<Metal> metalList;
    private final List<Ingot> ingotsList;

    public MetalAndIngotListDTO(List<Ingot> ingotsList, List<Metal> metalsList ){
        metalList = metalsList;
        this.ingotsList = ingotsList;
    }

    public List<Metal> getMetalList() {
        return this.metalList;
    }

    public List<Ingot> getIngotsList() {
        return this.ingotsList;
    }


    public Map<Integer, Metal> getMetalMap(){

        Map<Integer, Metal> metalsMap = new HashMap<>();

        for ( Metal metal : this.metalList) {
            metalsMap.put(metal.getId(), metal);
        }

        return metalsMap;
    }

    public Map<Integer, Ingot> getIngotMap(){

        Map<Integer, Ingot> ingotsMap = new HashMap<>();

        for ( Ingot ingot : this.ingotsList) {
            ingotsMap.put(ingot.getMetalID(), ingot);
        }

        return ingotsMap;
    }
}
