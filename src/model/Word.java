package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Word {
    private String word;
    private char punctuationAfter;

    public Word(String word) {
        List<String> tokens = Arrays.stream(word.split("(?<=\\p{L})(?=\\p{Punct})|(?<=\\p{Punct})(?=\\p{L})"))
                .map(String::trim)
                .toList();

        this.word = tokens.get(0);
        if (tokens.size() > 1) {
            this.punctuationAfter = tokens.get(1).charAt(0);
        }
    }

    public Word(List<Character> word, char punctuationAfter) {
        this.word = word.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        if(punctuationAfter != 0) {
            this.punctuationAfter = punctuationAfter;
        }
    }

    public void capitalizeFirstLetter() {
        this.word = word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    @Override
    public String toString() {
        if (punctuationAfter != 0) {
            return word + punctuationAfter;
        }

        return word;
    }

    /***
     * Parses word into characters without punctuation.
     * @return A list of characters in the word in original order, without punctuation.
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
