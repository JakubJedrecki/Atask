package com.example.task.interviewtask.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {
    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("GBP")
    @Expose
    private Double gBP;
    @SerializedName("EUR")
    @Expose
    private Double eUR;
    @SerializedName("PLN")
    @Expose
    private Double pLN;
    @SerializedName("AUD")
    @Expose
    private Double aUD;
    @SerializedName("JPY")
    @Expose
    private Double jPY;

    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    public Double getGBP() {
        return gBP;
    }

    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }

    public Double getEUR() {
        return eUR;
    }

    public void setEUR(Double eUR) {
        this.eUR = eUR;
    }

    public Double getPLN() {
        return pLN;
    }

    public void setPLN(Double pLN) {
        this.pLN = pLN;
    }

    public Double getAUD() {
        return aUD;
    }

    public void setAUD(Double aUD) {
        this.aUD = aUD;
    }

    public Double getJPY() {
        return jPY;
    }

    public void setJPY(Double jPY) {
        this.jPY = jPY;
    }
}
