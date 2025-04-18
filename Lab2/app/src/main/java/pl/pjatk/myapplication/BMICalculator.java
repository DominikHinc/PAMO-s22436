package pl.pjatk.myapplication;

public class BMICalculator {

    public static boolean isValidNumber(String value) {
        try {
            int number = Integer.parseInt(value);
            return number >= 1 && number <= 300;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double calculateBMI(int weight, int height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

}
