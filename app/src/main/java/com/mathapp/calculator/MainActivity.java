package com.mathapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private Calculate calculate;
    private String input = "("; // (...operations...)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.editTextEquation);
        calculate = new Calculate();
    }

    public void buttonClick(View view) {
        double result;
        String show = display.getText().toString();

        switch (view.getId()) {
            case R.id.button0:
                show = show.concat("0");
                display.setText(show);
                break;
            case R.id.button1:
                show = show.concat("1");
                display.setText(show);
                break;
            case R.id.button2:
                show = show.concat("2");
                display.setText(show);
                break;
            case R.id.button3:
                show = show.concat("3");
                display.setText(show);
                break;
            case R.id.button4:
                show = show.concat("4");
                display.setText(show);
                break;
            case R.id.button5:
                show = show.concat("5");
                display.setText(show);
                break;
            case R.id.button6:
                show = show.concat("6");
                display.setText(show);
                break;
            case R.id.button7:
                show = show.concat("7");
                display.setText(show);
                break;
            case R.id.button8:
                show = show.concat("8");
                display.setText(show);
                break;
            case R.id.button9:
                show = show.concat("9");
                display.setText(show);
                break;
            case R.id.buttonAddition:
                show = show.concat("+");
                display.setText(show);
                break;
            case R.id.buttonSubtraction:
                show = show.concat("-");
                display.setText(show);
                break;
            case R.id.buttonMultiplication:
                show = show.concat("*");
                display.setText(show);
                break;
            case R.id.buttonDivision:
                show = show.concat("/");
                display.setText(show);
                break;
            case R.id.buttonFactorial:
                show = show.concat("!");
                display.setText(show);
                break;
            case R.id.buttonExponent:
                show = show.concat("^");
                display.setText(show);
                break;
            case R.id.buttonPercentage:
                show = show.concat("%(");
                display.setText(show);
                break;
            case R.id.buttonSquareRoot:
                show = show.concat("√(");
                display.setText(show);
                break;
            case R.id.buttonDot:
                show = show.concat(".");
                display.setText(show);
                break;
            case R.id.buttonOpenParenthesis:
                show = show.concat("(");
                display.setText(show);
                break;
            case R.id.buttonCloseParenthesis:
                show = show.concat(")");
                display.setText(show);
                break;
            case R.id.buttonDelete:
                if(show.length() != 0) {
                    if(show.length() == 1)
                        show = "";
                    else
                        show = show.substring(0, show.length()-1); // substring(inclusive, exclusive)
                }
                display.setText(show);
                break;
            case R.id.buttonClear:
                show = "";
                display.setText(show);
                break;
            case R.id.buttonEqual:
                input = input.concat(show).concat(")");
                result = calculate.evaluate(input);
                if(result % 1 == 0) // no decimal part (integer)
                {
                    int cast = (int)result; // cast to integer
                    display.setText(Integer.toString(cast));
                }else{
                    display.setText(Double.toString(result));
                }
                input = "("; // be ready for the next operation
                break;
        }
    }
}