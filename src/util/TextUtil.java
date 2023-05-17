package util;

import com.sun.jdi.event.StepEvent;
import model.Sentence;
import model.Word;

import java.util.*;
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

    public static String reverseCharsInWord(String text) {
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

    public static String reverseWordsInSentence(String text) {
        List<Sentence> sentences = parseTextToSentences(text);
        List<Sentence> modifiedSentences = new ArrayList<>();

        for(Sentence sentence : sentences) {
            List<Word> words = new ArrayList<>(sentence.getWords());

            // Remove capital letter from the first word
            Word initialWord = words.get(0);
            initialWord.setWord(
                    initialWord.getWord().substring(0,1).toLowerCase() + initialWord.getWord().substring(1));

            Collections.reverse(words);

            Sentence reversedSentence = new Sentence(words, sentence.getEndingCharacter());
            reversedSentence.capitalizeFirstLetter();
            modifiedSentences.add(reversedSentence);
        }

        return buildStringFromSentences(modifiedSentences);
    }

    public static Map<Character, Long> countVowelsInText(String text) {
        List<Sentence> sentences = parseTextToSentences(text);

        Map<Character, Long> textVowelCount = new HashMap<>();
        for(Sentence sentence : sentences) {
            Map<Character, Long> sentenceVowelCount = new HashMap<>(sentence.countVowels());

            for(Character vowel : sentenceVowelCount.keySet()) {
                Long previousCount = textVowelCount.get(vowel);

                if(previousCount == null) {
                    textVowelCount.put(vowel, sentenceVowelCount.get(vowel));
                } else {
                    textVowelCount.put(vowel, previousCount + sentenceVowelCount.get(vowel));
                }
            }
        }

        return textVowelCount;
    }

    public static String countVowelsInEachSentence(String text) {
        List<Sentence> sentences = parseTextToSentences(text);
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;

        for(Sentence sentence : sentences) {
            stringBuilder.append("Broj samoglasnika u " + ++count + ". rečenici: " + sentence.countVowels() + ". ");
        }

        return stringBuilder.toString();
    }

    public static String reverseSentencesInText(String text) {
        List<Sentence> sentences = new ArrayList<>(parseTextToSentences(text));
        Collections.reverse(sentences);
        return buildStringFromSentences(sentences);
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