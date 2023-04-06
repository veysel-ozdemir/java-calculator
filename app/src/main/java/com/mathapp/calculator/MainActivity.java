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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.editTextEquation);
        calculate = new Calculate();

        // trial
        String input = "23+1*3-(1-(4/2))";
        Calculate calculate = new Calculate();
        System.out.println(calculate.evaluate(input));
    }

    public void buttonClick(View view) {
        int result;
        String input = display.getText().toString();

        switch (view.getId()) {
            case R.id.button0:
                input = input.concat("0");
                display.setText(input);
                break;
            case R.id.button1:
                input = input.concat("1");
                display.setText(input);
                break;
            case R.id.button2:
                input = input.concat("2");
                display.setText(input);
                break;
            case R.id.button3:
                input = input.concat("3");
                display.setText(input);
                break;
            case R.id.button4:
                input = input.concat("4");
                display.setText(input);
                break;
            case R.id.button5:
                input = input.concat("5");
                display.setText(input);
                break;
            case R.id.button6:
                input = input.concat("6");
                display.setText(input);
                break;
            case R.id.button7:
                input = input.concat("7");
                display.setText(input);
                break;
            case R.id.button8:
                input = input.concat("8");
                display.setText(input);
                break;
            case R.id.button9:
                input = input.concat("9");
                display.setText(input);
                break;
            case R.id.buttonAddition:
                input = input.concat("+");
                display.setText(input);
                break;
            case R.id.buttonSubtraction:
                input = input.concat("-");
                display.setText(input);
                break;
            case R.id.buttonMultiplication:
                input = input.concat("*");
                display.setText(input);
                break;
            case R.id.buttonDivision:
                input = input.concat("/");
                display.setText(input);
                break;
            case R.id.buttonFactorial:
                input = input.concat("!");
                display.setText(input);
                break;
            case R.id.buttonExponent:
                input = input.concat("^");
                display.setText(input);
                break;
            case R.id.buttonPercentage:
                input = input.concat("%(");
                display.setText(input);
                break;
            case R.id.buttonSquareRoot:
                input = input.concat("√(");
                display.setText(input);
                break;
            case R.id.buttonDot:
                input = input.concat(".");
                display.setText(input);
                break;
            case R.id.buttonOpenParenthesis:
                input = input.concat("(");
                display.setText(input);
                break;
            case R.id.buttonCloseParenthesis:
                input = input.concat(")");
                display.setText(input);
                break;
            case R.id.buttonDelete:
                if(input.length() != 0) {
                    if(input.length() == 1)
                        input = "";
                    else
                        input = input.substring(0, input.length()-1); // substring(inclusive, exclusive)
                }
                display.setText(input);
                break;
            case R.id.buttonClear:
                input = "";
                display.setText(input);
                break;
            case R.id.buttonEqual:
                result = calculate.evaluate(input);
                display.setText(Integer.toString(result));
                break;
        }
    }
}