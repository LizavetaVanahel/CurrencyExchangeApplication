package com.example.vanahel.currencyexchangeapplication.fragments.curgraphic;

import java.util.Map;

public interface CurrencyGraphicView {

    void showGraphic( Map<Integer, Float> rateDynamics );

    void showError ( String error );

}
