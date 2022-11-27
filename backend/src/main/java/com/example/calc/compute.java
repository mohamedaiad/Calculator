package com.example.calc;

import org.apache.catalina.startup.AddPortOffsetRule;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/calculate")
public class compute {
    @PostMapping("/expression")

    public String getParam(@RequestBody String expression){
        expression = expression.substring(0,expression.length()-1);
        expression = expression.replaceAll(" ","");

        double first=0,second=0;
        char op = 0;
        String s="";
        int index=0;
        if(expression.charAt(0) == '-') {s+="-"; index=1;}
        for(int i = index;i<expression.length();i++){
            if ((expression.charAt(i) >= '0' && expression.charAt(i) <= '9') || expression.charAt(i) == '.') {
                s += expression.charAt(i);
            }
            else {
                op = expression.charAt(i);
                first = Double.parseDouble(s);
                s = expression.substring(i+1,expression.length());
                if(s=="" && op != 's' && op !='r' && op !='n' && op !='%') return Double.toString(first);
                else if (s!= "") second = Double.parseDouble(s);
                break;
            }
        }
        if(op == 0) {
            first = Double.parseDouble(s);
            return Double.toString(first);
        }

        switch (op){
            case '+' : return  add(first,second);
            case  '-' : return  subtract(first,second);
            case '*' : return  multiply(first,second);
            case '/' : return  division(first,second);
            case '%' : return  percentage(first);
            case 's' : return  square(first);
            case 'r' : return  root(first);
            case 'n' : return  invert(first);
        }
        return  "ERROR!";
    }

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
        double res = Math.pow(first,2);
        return Double.toString(res);
    }

    public String root(double first){
        double res = Math.sqrt(first);
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
