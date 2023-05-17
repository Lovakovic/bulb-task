package util;

import model.Sentence;
import model.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TextUtil {
    public static String randomizeInnerChars(String text) {
        List<Sentence> sentences = parseTextToSentences(text);
        List<Sentence> modifiedSentences = new ArrayList<>();

        // Sentence parsing
        for(Sentence sentence : sentences) {
            List<Word> words = sentence.getWords();

            List<Word> newSentenceWords = new ArrayList<>();
            // Word permutation
            for(Word word : words) {
                List<Character> wordCharacters = new ArrayList<>(word.getCharacters());

                char[] firstLastChar = {
                        wordCharacters.remove(0),
                        wordCharacters.remove(wordCharacters.size()-1)
                };

                Collections.shuffle(wordCharacters);
                wordCharacters.add(0, firstLastChar[0]);
                wordCharacters.add(firstLastChar[1]);

                newSentenceWords.add(new Word(wordCharacters, word.getPunctuationAfter()));
            }

            modifiedSentences.add(new Sentence(newSentenceWords, sentence.getEndingCharacter()));
        }

        return buildStringFromSentences(modifiedSentences).trim();
    }

    public static String reverseWords(String text) {
        List<Sentence> sentences = parseTextToSentences(text);
        List<Sentence> modifiedSentences = new ArrayList<>();

        for(Sentence sentence : sentences) {
            List<Word> words = sentence.getWords();

            List<Word> reversedWords = new ArrayList<>();
            for(Word word : words) {
                StringBuilder reversedWord = new StringBuilder(word.getWord().toLowerCase());

                reversedWords.add(new Word(reversedWord.reverse().toString() + word.getPunctuationAfter()));
            }

            Sentence newSentence = new Sentence(reversedWords, sentence.getEndingCharacter());
            newSentence.capitalizeFirstLetter();
            modifiedSentences.add(newSentence);
        }

        return buildStringFromSentences(modifiedSentences);
    }

    private static List<Sentence> parseTextToSentences(String text) {
        return Arrays.stream(text.replace("\n", " ").split("(?<=[.!?])\\s+"))
                .map(Sentence::new)
                .toList();
    }

    private static String buildStringFromSentences(List<Sentence> sentences) {
        return sentences.stream().map(Sentence::toString).collect(Collectors.joining());
    }
}