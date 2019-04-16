package com.mad.stockquotes;

public class ApiResponse {
    public final Stock stock;
    public final Throwable error;

    public ApiResponse (Stock stock, Throwable  error){
        this.stock  = stock;
        this.error = error;
    }
}
