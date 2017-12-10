package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingot {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("MetalID")
    @Expose
    private Integer metalID;
    @SerializedName("Nominal")
    @Expose
    private Double nominal;
    @SerializedName("NoCertificateDollars")
    @Expose
    private Object noCertificateDollars;
    @SerializedName("NoCertificateRubles")
    @Expose
    private Double noCertificateRubles;
    @SerializedName("CertificateDollars")
    @Expose
    private Object certificateDollars;
    @SerializedName("CertificateRubles")
    @Expose
    private Double certificateRubles;
    @SerializedName("BanksDollars")
    @Expose
    private Object banksDollars;
    @SerializedName("BanksRubles")
    @Expose
    private Double banksRubles;
    @SerializedName("EntitiesDollars")
    @Expose
    private Object entitiesDollars;
    @SerializedName("EntitiesRubles")
    @Expose
    private Double entitiesRubles;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMetalID() {
        return this.metalID;
    }

    public void setMetalID(Integer metalID) {
        this.metalID = metalID;
    }

    public Double getNominal() {
        return this.nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Object getNoCertificateDollars() {
        return this.noCertificateDollars;
    }

    public void setNoCertificateDollars(Object noCertificateDollars) {
        this.noCertificateDollars = noCertificateDollars;
    }

    public Double getNoCertificateRubles() {
        return this.noCertificateRubles;
    }

    public void setNoCertificateRubles(Double noCertificateRubles) {
        this.noCertificateRubles = noCertificateRubles;
    }

    public Object getCertificateDollars() {
        return this.certificateDollars;
    }

    public void setCertificateDollars(Object certificateDollars) {
        this.certificateDollars = certificateDollars;
    }

    public Double getCertificateRubles() {
        return this.certificateRubles;
    }

    public void setCertificateRubles(Double certificateRubles) {
        this.certificateRubles = certificateRubles;
    }

    public Object getBanksDollars() {
        return this.banksDollars;
    }

    public void setBanksDollars(Object banksDollars) {
        this.banksDollars = banksDollars;
    }

    public Double getBanksRubles() {
        return this.banksRubles;
    }

    public void setBanksRubles(Double banksRubles) {
        this.banksRubles = banksRubles;
    }

    public Object getEntitiesDollars() {
        return this.entitiesDollars;
    }

    public void setEntitiesDollars(Object entitiesDollars) {
        this.entitiesDollars = entitiesDollars;
    }

    public Double getEntitiesRubles() {
        return this.entitiesRubles;
    }

    public void setEntitiesRubles(Double entitiesRubles) {
        this.entitiesRubles = entitiesRubles;
    }

}
