package task1;

public class Utils {

    // loop type
    public static int getFactorial_loop(int value) {
        for (int i = value - 1; i >= 1; i--) {
            value = value * i;
        }
        return value;
    }

    // recursive type
    public static int getFactorial_recursive(int value) {
        return (value == 1) ? 1 : value * getFactorial_recursive(value - 1);
    }




}
