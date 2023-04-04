package com.mathapp.calculator;

public class Calculate {
    private int firstNo, secondNo;

    public Calculate(int firstNo, int secondNo) {
        this.firstNo = firstNo;
        this.secondNo = secondNo;
    }

    public int add(){
        return firstNo + secondNo;
    }

    public int sub(){
        return firstNo - secondNo;
    }

    public int mult(){
        return firstNo * secondNo;
    }

    public int div(){
        return firstNo / secondNo;
    }
}
