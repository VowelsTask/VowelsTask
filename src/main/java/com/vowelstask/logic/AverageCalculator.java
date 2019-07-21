package com.vowelstask.logic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.vowelstask.io.PropertiesLoader.VOWELS;
import static com.vowelstask.io.PropertiesLoader.getProperty;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class AverageCalculator {

    public Map<String, Double> calculateVowelsAverageNumberPerVowelsSetAndWordLength(List<String> words)
    {
        return words
                .stream()
                .sorted(comparing(String::length).reversed())
                .collect(
                        groupingBy(
                                word -> getUniqueSortedListOfVowels(word) + ", " + word.length(),
                                LinkedHashMap::new,
                                averagingLong(this::getNumberOfVowels)
                        )
                );
    }

    private List<Character> getUniqueSortedListOfVowels(String word)
    {
        return getVowelsStream(word)
                .distinct()
                .sorted()
                .collect(toList());
    }

    private Long getNumberOfVowels(String word)
    {
        return getVowelsStream(word)
                .count();
    }

    private Stream<Character> getVowelsStream(String word)
    {
        return word
                .chars()
                .mapToObj(integerValueOfChar -> (char) integerValueOfChar)
                .filter(letter -> getProperty(VOWELS)
                        .toLowerCase()
                        .contains(letter.toString()));
    }
}
