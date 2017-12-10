package com.example.vanahel.currencyexchangeapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.example.vanahel.currencyexchangeapplication.R.string;
import com.example.vanahel.currencyexchangeapplication.fragments.aboutapp.AboutAppFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.curcalculator.CurrencyCalculatorFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.curgraphic.CurrencyGraphicFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.favoritecur.FavoriteCurrenciesFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.majorcur.MajorCurrencyRateFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.metalsrate.IngotsRateFragment;
import com.example.vanahel.currencyexchangeapplication.fragments.particulardaterate.ParticularDateCurrencyRateFragment;

public class HomeActivity extends AppCompatActivity
        implements OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_home);
        Toolbar toolbar = this.findViewById(id.toolbar);
        this.setSupportActionBar(toolbar);


        DrawerLayout drawer = this.findViewById(id.home_activity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = this.findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        this.displayView(id.major_currency_rate);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = this.findViewById(id.home_activity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        this.displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = this.getString(string.app_name);

        switch (viewId) {
            case id.major_currency_rate:
                fragment = new MajorCurrencyRateFragment();
                title = this.getString(string.major_currency_rate_title);
                break;
            case id.rate_dynamics_graphic:
                fragment = new CurrencyGraphicFragment();
                title = this.getString(string.rate_dynamics_graphic_title);
                break;
            case id.particular_date_rate:
                fragment = new ParticularDateCurrencyRateFragment();
                title = this.getString(string.particular_date_rate_title);
                break;
            case id.metals_rate:
                fragment = new IngotsRateFragment();
                title = this.getString(string.metals_rate_title);
                break;
            case id.currency_calculator:
                fragment = new CurrencyCalculatorFragment();
                title = this.getString(string.currency_calculator_title);
                break;
            case id.favorite_currency:
                fragment = new FavoriteCurrenciesFragment();
                title = this.getString(string.favorite_currency_title);
                break;
            case id.about_app:
                fragment = new AboutAppFragment();
                title = this.getString(string.about_app_title);
                break;


        }

        if (fragment != null) {
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.replace(id.content_frame, fragment);
            ft.commit();
        }

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = this.findViewById(id.home_activity);
        drawer.closeDrawer(GravityCompat.START);

    }
}
