package model;

import java.util.Arrays;
import java.util.List;

public class Sentence {
    private List<Word> words;
    private Character endingCharacter;

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

    public Sentence(List<Word> words, Character endingCharacter) {
        this.words = words;
        if(endingCharacter != ' ') {
            this.endingCharacter = endingCharacter;
        }
    }

    @Override
    public String toString() {
        return this.words.toString() + this.endingCharacter;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Character getEndingCharacter() {
        return endingCharacter;
    }

    public void setEndingCharacter(Character endingCharacter) {
        this.endingCharacter = endingCharacter;
    }
}