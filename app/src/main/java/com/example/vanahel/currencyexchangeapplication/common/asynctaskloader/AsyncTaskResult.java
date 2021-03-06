package com.example.vanahel.currencyexchangeapplication.common.asynctaskloader;

public class AsyncTaskResult<T> {

    private T result;
    private RuntimeException error;

    public T getResult() {
        return result;
    }

    void setResult( T result ) {
        this.result = result;
    }

    public RuntimeException getError() {
        return error;
    }

    void setError( RuntimeException error ) {
        this.error = error;
    }

}
