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
}
