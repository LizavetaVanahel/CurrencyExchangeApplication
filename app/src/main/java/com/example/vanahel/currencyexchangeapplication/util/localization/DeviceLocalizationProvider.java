package com.example.vanahel.currencyexchangeapplication.util.localization;

import java.util.Locale;

public class DeviceLocalizationProvider {

    public String getCurrentLanguage (){

        return Locale.getDefault().getLanguage();
    }
}
