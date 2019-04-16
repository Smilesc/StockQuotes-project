package com.mad.stockquotes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;

public class RequestStocks extends AsyncTask <String, Void, ApiResponse> {
    TextView lastTradeTime;
    TextView lastTradePrice;
    TextView change;
    TextView range;
    TextView name;
    TextView symbol;
    TextView notValidStock;
    Context context;


    public RequestStocks(TextView lastTradeTime, TextView lastTradePrice,
                         TextView change, TextView range, TextView name, TextView symbol,
                         Context context){
        this.lastTradeTime = lastTradeTime;
        this.lastTradePrice = lastTradePrice;
        this.change = change;
        this.range = range;
        this.name = name;
        this.symbol = symbol;

        this.context = context;
    }
    @Override
    protected ApiResponse doInBackground(String... strings) {
        Stock stock = new Stock(strings[0]);
//        Stock stock = new Stock(this.stockName);
        try {
            stock.load();
        } catch (MalformedURLException m){
            Log.i("MALFORMED URL EXCEPTION", m.getMessage());
        } catch (IOException i){
            Log.i("IO EXCEPTION", i.getMessage());
            return new ApiResponse(null, i);
        }
        return new ApiResponse(stock, null);
    }

    @Override
    protected void onPostExecute(ApiResponse apiResponse) {
        super.onPostExecute(apiResponse);
        if(apiResponse.error == null){
            lastTradeTime.setText(context.getResources().getString(R.string.lastTradeTime, apiResponse.stock.getLastTradeTime()));
            lastTradePrice.setText(context.getResources().getString(R.string.lastTradePrice, apiResponse.stock.getLastTradePrice()));
            change.setText(context.getResources().getString(R.string.change, apiResponse.stock.getChange()));
            range.setText(context.getResources().getString(R.string.range, apiResponse.stock.getRange()));
            name.setText(context.getResources().getString(R.string.name, apiResponse.stock.getName()));
            symbol.setText(context.getResources().getString(R.string.symbol, apiResponse.stock.getSymbol()));
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.notValidStock), Toast.LENGTH_LONG).show();
            lastTradeTime.setText(context.getResources().getString(R.string.lastTradeTimeDefault));
            lastTradePrice.setText(context.getResources().getString(R.string.lastTradePriceDefault));
            change.setText(context.getResources().getString(R.string.changeDefault));
            range.setText(context.getResources().getString(R.string.rangeDefault));
            name.setText(context.getResources().getString(R.string.nameDefault));
            symbol.setText(context.getResources().getString(R.string.symbolDefault));
        }


    }
}
