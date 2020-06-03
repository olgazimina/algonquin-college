import java.security.SecureRandom;

public class Lab6 {
    private static final int TOTAL_NUMBERS    = 100;
    private static final int NUMBER_OF_RANGES = 6;

    private static void print(String message, int control) {
        for (int i = 0; i < control; i++) {
            System.out.print(message);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[]        temperatureData = new int[TOTAL_NUMBERS];
        int[]        frequencyOfTemp = new int[NUMBER_OF_RANGES];
        int          maxTemp         = Integer.MIN_VALUE;
        int          minTemp         = Integer.MAX_VALUE;
        int          sum             = 0;
        double       avergageTemp    = 0.0;
        SecureRandom secureRandom    = new SecureRandom();

        for (int i = 0; (i < temperatureData.length); i++) {
            int currentTemp = secureRandom.nextInt(81) - 40;

            // fill in the frequency array
            if (currentTemp < -10) {
                frequencyOfTemp[0]++;
            } else if (currentTemp < 0) {
                frequencyOfTemp[1]++;
            } else if (currentTemp < 10) {
                frequencyOfTemp[2]++;
            } else if (currentTemp < 20) {
                frequencyOfTemp[3]++;
            } else if (currentTemp < 30) {
                frequencyOfTemp[4]++;
            } else if (currentTemp > 30) {
                frequencyOfTemp[5]++;
            }

            // search for min and max
            if (currentTemp < minTemp) {
                minTemp = currentTemp;
            } else if (currentTemp > maxTemp) {
                maxTemp = currentTemp;
            }

            // calculate the total sum of temperatures
            sum += currentTemp;

            // push current temperature into array
            temperatureData[i] = currentTemp;
        }

        // calculate average temperature
        avergageTemp = sum / TOTAL_NUMBERS;


        System.out.println("Program made by Olga Zimina\n");
        System.out.println("Maximum temperature: " + maxTemp);
        System.out.println("Minimum temperature: " + minTemp);
        System.out.println("Average temperature: " + avergageTemp + "\n");
        System.out.println("Frequency of Temperature Ranges:");

        System.out.print("Less than -10: ");
        print("*", frequencyOfTemp[0]);

        System.out.print("-10 to 0: ");
        print("*", frequencyOfTemp[1]);

        System.out.print("0 to 10: ");
        print("*", frequencyOfTemp[2]);

        System.out.print("10 to 20: ");
        print("*", frequencyOfTemp[3]);

        System.out.print("20 to 30: ");
        print("*", frequencyOfTemp[4]);

        System.out.print("Greater than 30: ");
        print("*", frequencyOfTemp[5]);
    }
}
