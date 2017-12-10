package com.example.vanahel.currencyexchangeapplication.util.propertyreader;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class MajorCurrencyIdProvider {

    private final Properties properties = new Properties();

    public Set<Integer> provideMajorCurrenciesId(Context context)  {

        Set<Integer> majorCurrenciesIds = new HashSet<>();

        AssetManager assetManager = context.getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("majorCurrenciesIds.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String udsId = properties.getProperty("UsdId");
        String eurId = properties.getProperty("EurId");
        String rubId = properties.getProperty("RubId");

        majorCurrenciesIds.add(Integer.parseInt(udsId));
        majorCurrenciesIds.add(Integer.parseInt(eurId));
        majorCurrenciesIds.add(Integer.parseInt(rubId));

            return majorCurrenciesIds;

        }


}
