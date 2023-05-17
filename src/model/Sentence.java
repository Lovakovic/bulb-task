package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {
    private List<Word> words;
    private char endingCharacter;

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

    public Sentence(List<Word> words, char endingCharacter) {
        this.words = words;
        if(endingCharacter != 0) {
            this.endingCharacter = endingCharacter;
        }
    }

    public void capitalizeFirstLetter() {
        Word firstWord = words.get(0);
        firstWord.capitalizeFirstLetter();
    }

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