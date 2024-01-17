import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        String[] words = {"hangman", "java", "programming", "computer", "developer", "code"};
        Random random = new Random();
        String selectedWord = words[random.nextInt(words.length)];

        char[] wordToGuess = selectedWord.toCharArray();
        char[] displayWord = new char[wordToGuess.length];
        boolean[] guessedLetters = new boolean[26];

        Scanner scanner = new Scanner(System.in);
        int incorrectGuesses = 0;
        for (int i = 0; i < wordToGuess.length; i++) {
            displayWord[i] = '_';
        }
        while (true) {
            System.out.println("Current Word: " + new String(displayWord));
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);
            boolean letterFound = false;
            for (int i = 0; i < wordToGuess.length; i++) {
                if (wordToGuess[i] == guess) {
                    displayWord[i] = guess;
                    letterFound = true;
                }
            }
            if (!letterFound) {
                incorrectGuesses++;
                displayHangman(incorrectGuesses);
                if (incorrectGuesses == 7) {
                    System.out.println("Sorry, you've run out of attempts. The word was: " + selectedWord);
                    break;
                }
            }
            if (new String(displayWord).equals(selectedWord)) {
                System.out.println("Congratulations! You guessed the word: " + selectedWord);
                break;
            }
        }

        scanner.close();
    }

    private static void displayHangman(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 1:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 6:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 7:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\ |");
                System.out.println("      |");
                System.out.println("=========");
                break;
        }
    }
}