package Exception;

public class DivisionByZero extends Exception {
    public static final String message = "division by zero";

    @Override
    public String getMessage() {
        return message;
    }
}
