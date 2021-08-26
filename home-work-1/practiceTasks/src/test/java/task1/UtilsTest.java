package task1;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void getFactorial_loop() {
        int factorial = Utils.getFactorial_loop(10);
        System.out.println("factorial = " + factorial);
        assert (factorial == 3628800) : "not correct";
    }

    @Test
    void getFactorial_recursive() {
        int factorial = Utils.getFactorial_recursive(10);
        System.out.println("factorial = " + factorial);
        assert (factorial == 3628800) : "not correct";
    }
}