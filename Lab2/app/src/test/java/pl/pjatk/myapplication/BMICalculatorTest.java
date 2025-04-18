package pl.pjatk.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;


public class BMICalculatorTest {

    @Test
    public void testIsValidNumber() {
        assertTrue(BMICalculator.isValidNumber("70"));
        assertTrue(BMICalculator.isValidNumber("1"));
        assertTrue(BMICalculator.isValidNumber("300"));

        assertFalse(BMICalculator.isValidNumber("0"));
        assertFalse(BMICalculator.isValidNumber("301"));
        assertFalse(BMICalculator.isValidNumber("abc"));
        assertFalse(BMICalculator.isValidNumber(""));
    }

    @Test
    public void testCalculateBMI() {
        double bmi = BMICalculator.calculateBMI(70, 170);
        assertEquals(24.22, bmi, 0.01);
    }
}