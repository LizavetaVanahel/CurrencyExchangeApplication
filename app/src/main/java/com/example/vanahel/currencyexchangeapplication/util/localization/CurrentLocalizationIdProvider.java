package com.example.vanahel.currencyexchangeapplication.util.localization;

import android.content.Context;

import java.util.Set;

public class CurrentLocalizationIdProvider {

    private final Context context;

    public CurrentLocalizationIdProvider(Context context){
        this.context = context;
    }

    public String provideCurrentLocalizationId (){

        String currentId = null;

        DeviceLocalizationProvider deviceLocalizationProvider = new DeviceLocalizationProvider();
        String currentLanguage = deviceLocalizationProvider.getCurrentLanguage();

        LocalizationIdProvider localizationIdProvider = new LocalizationIdProvider();
        Set<String> currentLanguageIds = localizationIdProvider.getLocalizationId(this.context);

        for ( String id : currentLanguageIds ) {
            if (currentLanguage.equals(id)){
                currentId = id;
            }
        }

        return  currentId;
    }


}
