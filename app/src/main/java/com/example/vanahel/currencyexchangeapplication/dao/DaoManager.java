package com.example.vanahel.currencyexchangeapplication.dao;

public class DaoManager {

    private static final DaoManager DAOManagerInstance = new DaoManager();
    private CurrencyDao currencyDao;

    private DaoManager() {
    }

    public static DaoManager getInstance() {
        return DAOManagerInstance;
    }

    public CurrencyDao getCurrencyDao() {
        if (null == currencyDao) {
            throw new NullPointerException("DAOManager: currencyDao object wasn't initialized");
        }
        return currencyDao;
    }

    public void setCurrencyDao(CurrencyDao taskDAO) {
        currencyDao = taskDAO;
    }
}
