package com.krisz.calculatoradvanced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    protected TextView _result;
    protected String _chain = "";
    protected String setOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _result = (TextView) findViewById(R.id.result);
        _result.setText(_chain);
    }

    private void updateString()
    {
        _result.setText(_chain);
    }

    protected void onNumberClick(View v)
    {
        Button number = (Button) v;
        _chain += number.getText();
        updateString();
    }

    protected void onClickOperator(View v)
    {
        Button operator = (Button) v;
        _chain += operator.getText();
        setOperator = operator.getText().toString();
        updateString();
    }

    private void Clear()
    {
        _chain = "";
        setOperator = "";
        updateString();
    }

    protected void onClickClear(View v)
    {
        Clear();
    }

    private double operate(String a, String b, String op)
    {
        switch (op)
        {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "*":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                return Double.valueOf(a) / Double.valueOf(b);
            default:
                return -1;
        }
    }

    protected void onClickEqual(View v)
    {
        String[] operation = _chain.split(Pattern.quote(setOperator));

        if(operation.length < 2)
            return;

        Double _Result = operate(operation[0], operation[1], setOperator);
        _result.setText(_chain + "\n" + String.valueOf(_Result));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Clear();
        }
        if(id == R.id.action_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}