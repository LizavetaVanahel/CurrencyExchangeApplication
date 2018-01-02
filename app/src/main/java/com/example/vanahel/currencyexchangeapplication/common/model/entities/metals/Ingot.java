package com.example.vanahel.currencyexchangeapplication.common.model.entities.metals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingot {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("MetalID")
    @Expose
    private int metalID;
    @SerializedName("Nominal")
    @Expose
    private double nominal;
    @SerializedName("NoCertificateDollars")
    @Expose
    private Object noCertificateDollars;
    @SerializedName("NoCertificateRubles")
    @Expose
    private double noCertificateRubles;
    @SerializedName("CertificateDollars")
    @Expose
    private Object certificateDollars;
    @SerializedName("CertificateRubles")
    @Expose
    private double certificateRubles;
    @SerializedName("BanksDollars")
    @Expose
    private Object banksDollars;
    @SerializedName("BanksRubles")
    @Expose
    private double banksRubles;
    @SerializedName("EntitiesDollars")
    @Expose
    private Object entitiesDollars;
    @SerializedName("EntitiesRubles")
    @Expose
    private double entitiesRubles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMetalID() {
        return metalID;
    }

    public void setMetalID(int metalID) {
        this.metalID = metalID;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public Object getNoCertificateDollars() {
        return noCertificateDollars;
    }

    public void setNoCertificateDollars(Object noCertificateDollars) {
        this.noCertificateDollars = noCertificateDollars;
    }

    public double getNoCertificateRubles() {
        return noCertificateRubles;
    }

    public void setNoCertificateRubles(double noCertificateRubles) {
        this.noCertificateRubles = noCertificateRubles;
    }

    public Object getCertificateDollars() {
        return certificateDollars;
    }

    public void setCertificateDollars(Object certificateDollars) {
        this.certificateDollars = certificateDollars;
    }

    public double getCertificateRubles() {
        return certificateRubles;
    }

    public void setCertificateRubles(double certificateRubles) {
        this.certificateRubles = certificateRubles;
    }

    public Object getBanksDollars() {
        return banksDollars;
    }

    public void setBanksDollars(Object banksDollars) {
        this.banksDollars = banksDollars;
    }

    public double getBanksRubles() {
        return banksRubles;
    }

    public void setBanksRubles(double banksRubles) {
        this.banksRubles = banksRubles;
    }

    public Object getEntitiesDollars() {
        return entitiesDollars;
    }

    public void setEntitiesDollars(Object entitiesDollars) {
        this.entitiesDollars = entitiesDollars;
    }

    public double getEntitiesRubles() {
        return entitiesRubles;
    }

    public void setEntitiesRubles(double entitiesRubles) {
        this.entitiesRubles = entitiesRubles;
    }

}
