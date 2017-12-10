package com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
        @SerializedName("Cur_ID")
        @Expose
        private Integer curID;
        @SerializedName("Cur_ParentID")
        @Expose
        private Integer curParentID;
        @SerializedName("Cur_Code")
        @Expose
        private String curCode;
        @SerializedName("Cur_Abbreviation")
        @Expose
        private String curAbbreviation;
        @SerializedName("Cur_Name")
        @Expose
        private String curName;
        @SerializedName("Cur_Name_Bel")
        @Expose
        private String curNameBel;
        @SerializedName("Cur_Name_Eng")
        @Expose
        private String curNameEng;
        @SerializedName("Cur_QuotName")
        @Expose
        private String curQuotName;
        @SerializedName("Cur_QuotName_Bel")
        @Expose
        private String curQuotNameBel;
        @SerializedName("Cur_QuotName_Eng")
        @Expose
        private String curQuotNameEng;
        @SerializedName("Cur_NameMulti")
        @Expose
        private String curNameMulti;
        @SerializedName("Cur_Name_BelMulti")
        @Expose
        private String curNameBelMulti;
        @SerializedName("Cur_Name_EngMulti")
        @Expose
        private String curNameEngMulti;
        @SerializedName("Cur_Scale")
        @Expose
        private Integer curScale;
        @SerializedName("Cur_Periodicity")
        @Expose
        private Integer curPeriodicity;
        @SerializedName("Cur_DateStart")
        @Expose
        private String curDateStart;
        @SerializedName("Cur_DateEnd")
        @Expose
        private String curDateEnd;

        public Integer getCurID() {
            return this.curID;
        }

        public void setCurID(Integer curID) {
            this.curID = curID;
        }

        public Integer getCurParentID() {
            return this.curParentID;
        }

        public void setCurParentID(Integer curParentID) {
            this.curParentID = curParentID;
        }

        public String getCurCode() {
            return this.curCode;
        }

        public void setCurCode(String curCode) {
            this.curCode = curCode;
        }

        public String getCurAbbreviation() {
            return this.curAbbreviation;
        }

        public void setCurAbbreviation(String curAbbreviation) {
            this.curAbbreviation = curAbbreviation;
        }

        public String getCurName() {
            return this.curName;
        }

        public void setCurName(String curName) {
            this.curName = curName;
        }

        public String getCurNameBel() {
            return this.curNameBel;
        }

        public void setCurNameBel(String curNameBel) {
            this.curNameBel = curNameBel;
        }

        public String getCurNameEng() {
            return this.curNameEng;
        }

        public void setCurNameEng(String curNameEng) {
            this.curNameEng = curNameEng;
        }

        public String getCurQuotName() {
            return this.curQuotName;
        }

        public void setCurQuotName(String curQuotName) {
            this.curQuotName = curQuotName;
        }

        public String getCurQuotNameBel() {
            return this.curQuotNameBel;
        }

        public void setCurQuotNameBel(String curQuotNameBel) {
            this.curQuotNameBel = curQuotNameBel;
        }

        public String getCurQuotNameEng() {
            return this.curQuotNameEng;
        }

        public void setCurQuotNameEng(String curQuotNameEng) {
            this.curQuotNameEng = curQuotNameEng;
        }

        public String getCurNameMulti() {
            return this.curNameMulti;
        }

        public void setCurNameMulti(String curNameMulti) {
            this.curNameMulti = curNameMulti;
        }

        public String getCurNameBelMulti() {
            return this.curNameBelMulti;
        }

        public void setCurNameBelMulti(String curNameBelMulti) {
            this.curNameBelMulti = curNameBelMulti;
        }

        public String getCurNameEngMulti() {
            return this.curNameEngMulti;
        }

        public void setCurNameEngMulti(String curNameEngMulti) {
            this.curNameEngMulti = curNameEngMulti;
        }

        public Integer getCurScale() {
            return this.curScale;
        }

        public void setCurScale(Integer curScale) {
            this.curScale = curScale;
        }

        public Integer getCurPeriodicity() {
            return this.curPeriodicity;
        }

        public void setCurPeriodicity(Integer curPeriodicity) {
            this.curPeriodicity = curPeriodicity;
        }

        public String getCurDateStart() {
            return this.curDateStart;
        }

        public void setCurDateStart(String curDateStart) {
            this.curDateStart = curDateStart;
        }

        public String getCurDateEnd() {
            return this.curDateEnd;
        }

        public void setCurDateEnd(String curDateEnd) {
            this.curDateEnd = curDateEnd;
        }
}
