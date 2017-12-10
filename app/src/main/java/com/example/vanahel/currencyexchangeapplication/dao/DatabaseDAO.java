package com.example.vanahel.currencyexchangeapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vanahel.currencyexchangeapplication.common.model.entities.currencies.CurrencyNameAndRateValue;

import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO implements CurrencyDao {

    private static final String DB_NAME = "Currency";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "CurrencyTable";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CURRENCY = "currency";
    private static final String COLUMN_RATE = "rate";

    private static final String DB_CREATE =
            "CREATE TABLE " + DB_TABLE + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_CURRENCY + " TEXT, " +
                    COLUMN_RATE + " DOUBLE);";

    private final Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseDAO(Context context) {
        this.context = context;
        open();
    }

    private void open() {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        if (dbHelper != null) dbHelper.close();
    }


    @Override
    public void save(CurrencyNameAndRateValue currencyNameAndRateValue) {
        ContentValues cv = new ContentValues();
            cv.put(COLUMN_CURRENCY, currencyNameAndRateValue.getCurName());
            cv.put(COLUMN_RATE, currencyNameAndRateValue.getRate());
            cv.put(COLUMN_ID, currencyNameAndRateValue.getId());
        database.insert(DB_TABLE, null, cv);
    }

    @Override
    public List<CurrencyNameAndRateValue> getCurrenciesAndRates() {
        List<CurrencyNameAndRateValue> currencies = new ArrayList<>();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
                int currencyNameIndex = cursor.getColumnIndexOrThrow(COLUMN_CURRENCY);
                int rateIndex = cursor.getColumnIndexOrThrow(COLUMN_RATE);
                int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                String currencyName = cursor.getString(currencyNameIndex);
                Double rate = cursor.getDouble(rateIndex);
                Integer id = cursor.getInt(idIndex);
                CurrencyNameAndRateValue currencyNameAndRateValue = new CurrencyNameAndRateValue(currencyName, rate);
                currencyNameAndRateValue.setId(id);
                currencies.add(currencyNameAndRateValue);
            }

        return currencies;
    }

    @Override
    public List<Integer> getFavoriteCurrencyIds (){
        List<Integer> ids = new ArrayList<>();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            Integer id = cursor.getInt(idIndex);
            ids.add(id);
        }

            return ids;
    }

    public List<Double> getRates (){

        List<Double> ratesList = new ArrayList<>();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_RATE);
            Double rate = cursor.getDouble(idIndex);
            ratesList.add(rate);
        }

        return ratesList;
    }


    @Override
    public void delete(CurrencyNameAndRateValue currencyNameAndRateValue) {
        database.delete(DB_TABLE, COLUMN_ID + "=?", new String[]{currencyNameAndRateValue.getId().toString()});

    }

    private class DBHelper extends SQLiteOpenHelper {

        private DBHelper(Context context, String name, CursorFactory factory,
                         int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }



}
