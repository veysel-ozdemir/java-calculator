package com.mathapp.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.math.BigDecimal;

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

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU) // required for the sqrt() method
    public void buttonClick(View view) {
        BigDecimal result;
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
                show = show.concat("%");
                display.setText(show);
                break;
            case R.id.buttonSquareRoot:
                show = show.concat("√");
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
                if(result.compareTo(BigDecimal.ZERO) >= 0){ // result is positive or zero
                    if(result.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) // if no remainder, then the result has no decimal part (integer)
                    {
                        display.setText(Integer.toString(result.intValue())); // return as integer
                    }else{
                        display.setText(result.toString());
                    }
                } else { // result is negative
                    if(result.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) // if no remainder, then the result has no decimal part (integer)
                    {
                        display.setText("(".concat(Integer.toString(result.intValue())).concat(")")); // return as integer
                    } else {
                        display.setText("(".concat(result.toString()).concat(")"));
                    }
                }
                input = "("; // be ready for the next operation
                break;
        }
    }
}