package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("Cur_ID")
    @Expose
    private Integer curID;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Cur_Abbreviation")
    @Expose
    private String curAbbreviation;
    @SerializedName("Cur_Scale")
    @Expose
    private Integer curScale;
    @SerializedName("Cur_Name")
    @Expose
    private String curName;
    @SerializedName("Cur_OfficialRate")
    @Expose
    private Double curOfficialRate;

    public Integer getCurID() {
        return this.curID;
    }

    public void setCurID(Integer curID) {
        this.curID = curID;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return this.curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public Integer getCurScale() {
        return this.curScale;
    }

    public void setCurScale(Integer curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return this.curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Double getCurOfficialRate() {
        return this.curOfficialRate;
    }

    public void setCurOfficialRate(Double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }

}