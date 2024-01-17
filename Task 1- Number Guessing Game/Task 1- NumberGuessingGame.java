import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {

    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int maxAttempts = 5;
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                int userGuess = scanner.nextInt();
                attempts++;
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); 
            }
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + randomNumber);
            }
        }
        scanner.close();
    }
}
