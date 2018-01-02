package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metal {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NameBel")
    @Expose
    private String nameBel;
    @SerializedName("NameEng")
    @Expose
    private String nameEng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBel() {
        return nameBel;
    }

    public void setNameBel(String nameBel) {
        this.nameBel = nameBel;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

}
