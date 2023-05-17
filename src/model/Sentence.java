package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a sentence.
 */
public class Sentence {
    private List<Word> words;
    private char endingCharacter;

    /**
     * Constructs a sentence from a string.
     *
     * @param sentence The string to construct the sentence from.
     */
    public Sentence(String sentence) {
        char endingCharacter = sentence.charAt(sentence.length()-1);
        if(endingCharacter == '.' || endingCharacter == '!' || endingCharacter == '?') {
            this.endingCharacter = endingCharacter;
            this.words = Arrays.stream(sentence.substring(0, sentence.length()-1).split("\\s+"))
                    .map(Word::new)
                    .toList();
        } else {
            this.words = Arrays.stream(sentence.split("\\s+"))
                    .map(Word::new)
                    .toList();
        }
    }

    /**
     * Constructs a sentence from a list of words and an ending character.
     *
     * @param words The list of words.
     * @param endingCharacter The ending character.
     */
    public Sentence(List<Word> words, char endingCharacter) {
        this.words = words;
        if(endingCharacter != 0) {
            this.endingCharacter = endingCharacter;
        }
    }

    /**
     * Counts the number of each vowel character in the sentence.
     *
     * @return A map with the vowel characters as keys and their counts as values.
     */
    public Map<Character, Long> countVowels() {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        String sentence = this.toString();

        Map<Character, Long> vowelCount = new HashMap<>();
        for(char vowel : vowels) {
            Long count = sentence.chars().filter(c -> c == vowel).count();
            vowelCount.put(vowel, count);
        }

        return vowelCount;
    }

    /**
     * Capitalizes the first letter of the first word in the sentence.
     */
    public void capitalizeFirstLetter() {
        Word firstWord = words.get(0);
        firstWord.capitalizeFirstLetter();
    }

    /**
     * Converts the sentence to a string.
     *
     * @return The string representation of the sentence.
     */
    @Override
    public String toString() {
        String sentence = this.words.stream().map(Word::toString).collect(Collectors.joining(" "));
        return sentence.trim() + (this.endingCharacter != 0 ? this.endingCharacter : "") + " ";
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public char getEndingCharacter() {
        return endingCharacter;
    }

    public void setEndingCharacter(char endingCharacter) {
        this.endingCharacter = endingCharacter;
    }
}