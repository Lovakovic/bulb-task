package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a word.
 */
public class Word {
    private String word;
    private char punctuationAfter;

    /**
     * Constructs a word from a string.
     *
     * @param word The string to construct the word from.
     */
    public Word(String word) {
        List<String> tokens = Arrays.stream(word.split("(?<=\\p{L})(?=\\p{Punct})|(?<=\\p{Punct})(?=\\p{L})"))
                .map(String::trim)
                .toList();

        this.word = tokens.get(0);
        if (tokens.size() > 1) {
            this.punctuationAfter = tokens.get(1).charAt(0);
        }
    }

    /**
     * Constructs a word from a list of characters and a punctuation character.
     *
     * @param word The list of characters.
     * @param punctuationAfter The punctuation character.
     */
    public Word(List<Character> word, char punctuationAfter) {
        this.word = word.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        if(punctuationAfter != 0) {
            this.punctuationAfter = punctuationAfter;
        }
    }

    /**
     * Capitalizes the first letter of the word.
     */
    public void capitalizeFirstLetter() {
        this.word = word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Converts the word to a string.
     *
     * @return The string representation of the word.
     */
    @Override
    public String toString() {
        if (punctuationAfter != 0) {
            return word + punctuationAfter;
        }

        return word;
    }

    /**
     * Parses the word into characters without punctuation.
     *
     * @return A list of characters in the word in the original order, without punctuation.
     */
    public List<Character> getCharacters() {
        return this.word.chars().mapToObj(c -> (char)c).toList();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char getPunctuationAfter() {
        return punctuationAfter != 0 ? punctuationAfter : 0;
    }

    public void setPunctuationAfter(Character punctuationAfter) {
        this.punctuationAfter = punctuationAfter;
    }
}
