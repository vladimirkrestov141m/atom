package ru.example.BullsAndCows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    private static final Logger log = LoggerFactory.getLogger(Game.class);
    public static StringBuilder typedWordChars;
    public static StringBuilder wordChars;

    public static void main(String[] args) throws IOException {
        log.info("Start game");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String typedWord;
        do {
            String word = getWord();
            int tryCount = 0;
            System.out.println("You have 10 attempts. Word contains " + word.length() + " chars.");
            System.out.println("Try write word:");

            do {
                typedWord = br.readLine();
                if (typedWord.toString().equals("/?")) {
                    System.out.println(word);
                } else {
                    typedWordChars = new StringBuilder(typedWord);
                    wordChars = new StringBuilder(word);
                    ++tryCount;
                    int bullsCount = calculateBulls();
                    int cowsCount = calculateCows();
                    System.out.println("Cows: " + cowsCount + ". Bulls: " + bullsCount);
                    System.out.println("Attempts left: " + (10-tryCount));
                }
            } while(!typedWord.equals(word) && tryCount < 10);

            System.out.println("Word was: " + word);
            if (tryCount == 10) {
                log.info("lose");
                System.out.println("You lose!");
            } else {
                log.info("win");
                System.out.println("You win!");
            }

            System.out.println("New game? (Y/N)");
            typedWord = br.readLine();
        } while(typedWord.toString().equals("Y"));

    }

    public static String getWord() throws IOException {
        Path path = Paths.get("dictionary.txt");
        Scanner scanner = new Scanner(path);
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, words.size());

        return words.get(randomIndex);
    }

    public static int calculateBulls() {
        int bullsCount = 0;

        for(int index = 0; index < typedWordChars.length(); ++index) {
            if (index < wordChars.length() && typedWordChars.charAt(index) == wordChars.charAt(index)) {
                ++bullsCount;
                typedWordChars.deleteCharAt(index);
                wordChars.deleteCharAt(index);
                --index;
            }
        }

        return bullsCount;
    }

    public static int calculateCows() {
        int cowsCount = 0;

        for(int index = 0; index < typedWordChars.length(); ++index) {
            for(int wordCharIndex = 0; wordCharIndex < wordChars.length(); ++wordCharIndex) {
                if (typedWordChars.charAt(index) == wordChars.charAt(wordCharIndex)) {
                    ++cowsCount;
                    typedWordChars.deleteCharAt(index);
                    wordChars.deleteCharAt(wordCharIndex);
                    --index;
                    break;
                }
            }
        }

        return cowsCount;
    }
}
