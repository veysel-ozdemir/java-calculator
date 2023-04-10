package com.mathapp.calculator;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class Calculate {
    private Stack<BigDecimal> numbers;
    private Stack<Character> operators;

    public Calculate() {
        numbers = new Stack<>();
        operators = new Stack<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU) // required for the sqrt() method
    public BigDecimal evaluate(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                BigDecimal num = new BigDecimal(Character.toString(c));

                while (i+1 < input.length() && Character.isDigit(input.charAt(i+1))) { // checking next char whether is a digit
                    BigDecimal nextDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                    num = num.multiply(BigDecimal.TEN).add(nextDigit); // first multiple by 10, then add the next digit
                    i++;
                }

                if(input.charAt(i+1) == '.') { // handling the double value
                    i++; // index of dot
                    BigDecimal fraction = new BigDecimal("0.1");
                    do{ // assume after dot is definitely a number
                        BigDecimal nxtDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                        num = num.add(nxtDigit.multiply(fraction));
                        fraction = fraction.multiply(new BigDecimal("0.1"));
                        i++;
                    } while(Character.isDigit(input.charAt(i+1)));
                }
                numbers.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    performOperation();
                }
                operators.push(c);
            } else if (c == '(' && input.charAt(i+1) == '-' && Character.isDigit(input.charAt(i+2))) { // handling negative signed numbers
                String no = Character.toString(input.charAt(++i)); // bypassed the '(' --> i = index of - sign
                no = no.concat(Character.toString(input.charAt(++i))); // i = index of the number after - sign
                BigDecimal negNum = new BigDecimal(no);

                while(i+1 < input.length() && Character.isDigit(input.charAt(i+1))) {
                    BigDecimal nextDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                    negNum = negNum.multiply(BigDecimal.TEN).subtract(nextDigit); // first multiple by 10, then subtract the next digit
                    i++;
                }

                if(input.charAt(i+1) == '.') { //handle the negative double number
                    i++; // index of dot
                    BigDecimal fraction = new BigDecimal("0.1");
                    do{ // assume after dot is definitely a number
                        BigDecimal nxtDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                        negNum = negNum.subtract(nxtDigit.multiply(fraction));
                        fraction = fraction.multiply(new BigDecimal("0.1"));
                        i++;
                    } while(Character.isDigit(input.charAt(i+1)));
                }

                if(input.charAt(i+1) == ')') {
                    numbers.push(negNum); // the negative number pushed to the stack
                    i++; // bypass the ')'
                }
            } else if(c == '(' && input.charAt(i+1) == '√' && Character.isDigit(input.charAt(i+2))) { // handling square root of the number
                i += 2; // bypassed this: (√
                String no = Character.toString(input.charAt(i)); // i = index of the number after '√'
                BigDecimal sqrtNum = new BigDecimal(no);

                while(i+1 < input.length() && Character.isDigit(input.charAt(i+1))) {
                    BigDecimal nextDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                    sqrtNum = sqrtNum.multiply(BigDecimal.TEN).add(nextDigit); // first multiple by 10, then add the next digit
                    i++;
                }

                if(input.charAt(i+1) == '.') { //handle the double number
                    i++; // index of dot
                    BigDecimal fraction = new BigDecimal("0.1");
                    do{ // assume after dot is definitely a number
                        BigDecimal nxtDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                        sqrtNum = sqrtNum.add(nxtDigit.multiply(fraction));
                        fraction = fraction.multiply(new BigDecimal("0.1"));
                        i++;
                    } while(Character.isDigit(input.charAt(i+1)));
                }

                if(input.charAt(i+1) == ')') {
                    sqrtNum = sqrtNum.sqrt(new MathContext(sqrtNum.scale()/2)); // solve the square root
                    numbers.push(sqrtNum); // the solution pushed to the stack
                    i++; // bypass the ')'
                }
            } else if(c == '(' && input.charAt(i+1) == '%' && (Character.isDigit(input.charAt(i+2)) || (input.charAt(i+2) == '('))) { // handling the percentage of a number (number: 89 --> %89 = 0.89)
                BigDecimal pctNum;

                if(Character.isDigit(input.charAt(i+2))) { // positive number
                    i += 2; // bypassed this: (%

                    String no = Character.toString(input.charAt(i)); // i = index of the number after '%'
                    pctNum = new BigDecimal(no);

                    while(i+1 < input.length() && Character.isDigit(input.charAt(i+1))) {
                        BigDecimal nextDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                        pctNum = pctNum.multiply(BigDecimal.TEN).add(nextDigit); // first multiple by 10, then add the next digit
                        i++;
                    }

                    if(input.charAt(i+1) == '.') { //handle the double number
                        i++; // index of dot
                        BigDecimal fraction = new BigDecimal("0.1");
                        do{ // assume after dot is definitely a number
                            BigDecimal nxtDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                            pctNum = pctNum.add(nxtDigit.multiply(fraction));
                            fraction = fraction.multiply(new BigDecimal("0.1"));
                            i++;
                        } while(Character.isDigit(input.charAt(i+1)));
                    }
                } else { // negative number
                    i += 3; // bypassed this (%(

                    String no = Character.toString(input.charAt(i)); // i = index of the - sign
                    no = no.concat(Character.toString(input.charAt(++i))); // i = index of the number after - sign
                    pctNum = new BigDecimal(no);

                    while(i+1 < input.length() && Character.isDigit(input.charAt(i+1))) {
                        BigDecimal nextDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                        pctNum = pctNum.multiply(BigDecimal.TEN).subtract(nextDigit); // first multiple by 10, then subtract the next digit
                        i++;
                    }

                    if(input.charAt(i+1) == '.') { //handle the negative double number
                        i++; // index of dot
                        BigDecimal fraction = new BigDecimal("0.1");
                        do{ // assume after dot is definitely a number
                            BigDecimal nxtDigit = new BigDecimal(Character.toString(input.charAt(i+1)));
                            pctNum = pctNum.subtract(nxtDigit.multiply(fraction));
                            fraction = fraction.multiply(new BigDecimal("0.1"));
                            i++;
                        } while(Character.isDigit(input.charAt(i+1)));
                    }

                    if(input.charAt(i+1) == ')')
                        i++; // bypass the ')'
                }

                if(input.charAt(i+1) == ')') {
                    pctNum = pctNum.divide(new BigDecimal("100")); // solve the percentage
                    numbers.push(pctNum); // the solution pushed to the stack
                    i++; // bypass the ')'
                }
            }else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    performOperation(); // calculate inside the parenthesis
                }
                operators.pop(); // remove '(' from stack
            }
        }
        while (!operators.isEmpty()) {
            performOperation();
        }
        return numbers.pop();
    }

    private void performOperation() {
        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();
        char op = operators.pop();
        BigDecimal result = new BigDecimal("0");
        switch(op) {
            case '+': result = num1.add(num2); break;
            case '-': result = num1.subtract(num2); break;
            case '*': result = num1.multiply(num2); break;
            case '/': result = num1.divide(num2); break;
        }
        numbers.push(result);
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }
}