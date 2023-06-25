package test.java;

import main.java.SimpleCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalcTest {
    @Test
    void testSumC1() {
        SimpleCalc calc = new SimpleCalc();
        assertEquals(4, calc.add(2, 2));
    }

    @Test
    void testSumC2() {
        SimpleCalc calc = new SimpleCalc();
        assertEquals(0, calc.add(2, -2));
    }
}