package com.calculator.Evalution;

public class math {
    public String add(double first,double second){
        double res = first + second;
        return Double.toString(res);
    }

    public String subtract(double first,double second){
        double res = first - second;
        return Double.toString(res);
    }

    public String multiply(double first,double second){
        double res = first * second;
        return Double.toString(res);
    }

    public String division(double first,double second){
        double res =0;
        if(second == 0) return "Cannot divide by zero";
        try {
            res = first / second;
        }
        catch (Exception e){
            return "ERROR!";
        }
        return Double.toString(res);

    }

    public String square(double first){
        double res = java.lang.Math.pow(first,2);
        return Double.toString(res);
    }

    public String root(double first){
        double res = java.lang.Math.sqrt(first);
        if(first < 0) return "ERROR!";
        return Double.toString(res);
    }

    public String invert(double first){
        double res = 1/first;
        if(first == 0) return "ERROR!";
        return Double.toString(res);
    }

    public String percentage(double first){
        double res = first/100;
        return Double.toString(res);
    }
}
