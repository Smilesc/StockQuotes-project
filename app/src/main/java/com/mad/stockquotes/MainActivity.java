package com.mad.stockquotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button search;
    EditText text;

    TextView lastTradeTime;
    TextView lastTradePrice;
    TextView change;
    TextView range;
    TextView name;
    TextView symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastTradeTime = findViewById(R.id.lastTradeTime);
        lastTradePrice = findViewById(R.id.lastTradePrice);
        change = findViewById(R.id.change);
        range = findViewById(R.id.range);
        name = findViewById(R.id.name);
        symbol = findViewById(R.id.symbol);

        search = findViewById(R.id.search);
        text = findViewById(R.id.text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestStocks requestStocks = new RequestStocks(lastTradeTime, lastTradePrice, change, range, name, symbol, getApplicationContext());
                requestStocks.execute(text.getText().toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", text.getText().toString());

        outState.putString("lastTradeTime", lastTradeTime.getText().toString());
        outState.putString("lastTradePrice", lastTradePrice.getText().toString());
        outState.putString("change", change.getText().toString());
        outState.putString("range", range.getText().toString());
        outState.putString("name", name.getText().toString());
        outState.putString("symbol", symbol.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

        text.setText(bundle.getString("text"));
        text.setSelection(text.getText().length());

        lastTradeTime.setText(bundle.getString("lastTradeTime"));
        lastTradePrice.setText(bundle.getString("lastTradePrice"));
        change.setText(bundle.getString("change"));
        range.setText(bundle.getString("range"));
        name.setText(bundle.getString("name"));
        symbol.setText(bundle.getString("symbol"));

    }
}
