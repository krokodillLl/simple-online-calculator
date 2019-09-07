package Logics;

import Exception.DivisionByZero;

public class MathOperation {

    public static double Sum(double a, double b) {
        return a + b;
    }

    public static double Sub(double a, double b) {
        return a - b;
    }

    public static double Mul(double a, double b) {
        return a * b;
    }

    public static double Div(double a, double b) throws ArithmeticException {
        if(b != 0.0) {
            return a / b;
        }
        else {
            throw new ArithmeticException("Division by zero");
        }
    }

}
