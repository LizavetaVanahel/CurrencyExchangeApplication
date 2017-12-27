package com.example.vanahel.currencyexchangeapplication.util.localization;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class LocalizationIdProvider {

    private final Properties properties = new Properties();

    public Set<String> getLocalizationId(Context context){

        Set<String> supportedLanguageIds = new HashSet<>();

        AssetManager assetManager = context.getAssets();

        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("supportedLanguages.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rusId = properties.getProperty("Rus");
        String engId = properties.getProperty("Eng");
        String belId = properties.getProperty("Bel");

        supportedLanguageIds.add(rusId);
        supportedLanguageIds.add(engId);
        supportedLanguageIds.add(belId);

        return supportedLanguageIds;

    }

}
