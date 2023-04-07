package com.mathapp.calculator;

import java.util.Stack;

public class Calculate {
    private Stack<Double> numbers;
    private Stack<Character> operators;

    public Calculate() {
        numbers = new Stack<>();
        operators = new Stack<>();
    }

    public double evaluate(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                double num = c - '0'; // the difference of their Unicode values will give us the integer value. (The Unicode values of the digits '0' through '9' are consecutive.)
                while (i+1 < input.length() && Character.isDigit(input.charAt(i+1))) { // checking next char whether is a char
                    num = num * 10 + (input.charAt(i+1) - '0'); // e.g. num = 2 * 10 + (3) => num = 23
                    i++;
                }
                if(input.charAt(i+1) == '.') { // handling the double value
                    i++; // index of dot
                    double fraction = 0.1;
                    do{ // assume after dot is definitely a number
                        num = num + fraction*(input.charAt(i+1) - '0');
                        fraction *= 0.1;
                        i++;
                    } while(Character.isDigit(input.charAt(i+1)));
                }
                numbers.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    performOperation();
                }
                operators.push(c);
            } else if (c == '(') {
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
        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char op = operators.pop();
        double result = 0;
        switch(op) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1 / num2; break;
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