public class TemperatureConverter {

    // ===== Conversion Methods (default precision: 2 decimals) =====

    public static double celsiusToFahrenheit(double celsius) {
        return round((celsius * 9 / 5) + 32, 2);
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return round((fahrenheit - 32) * 5 / 9, 2);
    }

    // ===== Overloaded Methods (custom precision) =====

    public static double celsiusToFahrenheit(double celsius, int precision) {
        return round((celsius * 9 / 5) + 32, precision);
    }

    public static double fahrenheitToCelsius(double fahrenheit, int precision) {
        return round((fahrenheit - 32) * 5 / 9, precision);
    }

    // ===== Temperature Table up to 90 =====

    public static void printTemperatureTable90() {
        System.out.println("Celsius\tFahrenheit");
        System.out.println("---------------------");

        for (int c = 0; c <= 90; c += 10) {
            double f = celsiusToFahrenheit(c);
            System.out.println(c + "\t\t" + f);
        }
    }

    // ===== Utility: Rounding Method =====

    private static double round(double value, int precision) {
        double factor = Math.pow(10, precision);
        return Math.round(value * factor) / factor;
    }

    // ===== Main Method for Testing =====

    public static void main(String[] args) {

        System.out.println("25°C to °F = " + celsiusToFahrenheit(25));
        System.out.println("77°F to °C = " + fahrenheitToCelsius(77));

        System.out.println("25°C to °F (4 decimals) = " + celsiusToFahrenheit(25, 4));

        System.out.println("\nTemperature Table (0 to 90):");
        printTemperatureTable90();
    }
}
