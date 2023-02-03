package com.calculator.Service;

import com.calculator.Evalution.math;
import org.springframework.stereotype.Service;

@Service
public class computeService {
    public String computeExpression(String expression){
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

        math math = new math();

        switch (op){
            case '+' : return  math.add(first,second);
            case  '-' : return  math.subtract(first,second);
            case '*' : return  math.multiply(first,second);
            case '/' : return  math.division(first,second);
            case '%' : return  math.percentage(first);
            case 's' : return  math.square(first);
            case 'r' : return  math.root(first);
            case 'n' : return  math.invert(first);
        }
        return null;
    }
}
