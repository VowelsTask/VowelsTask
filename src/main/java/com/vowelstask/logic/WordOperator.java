package com.vowelstask.logic;

import java.util.List;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;
import static com.vowelstask.io.PropertiesLoader.PUNCTUATION_CHARACTERS;
import static com.vowelstask.io.PropertiesLoader.getProperty;
import static java.util.stream.Collectors.toList;

public class WordOperator
{
    private List<String> words;

    public WordOperator(List<String> words)
    {
        this.words = words;
    }

    public List<String> getWords()
    {
        return words;
    }

    public WordOperator removePunctuation()
    {
        words = words
            .stream()
            .map(wordWithPunctuation -> wordWithPunctuation
                .replaceAll(getProperty(PUNCTUATION_CHARACTERS), EMPTY_STRING))
            .filter(word -> !word.equals(EMPTY_STRING))
            .collect(toList());
        return this;
    }

    public WordOperator convertToLowerCase()
    {
        words = words
            .stream()
            .map(String::toLowerCase)
            .collect(toList());
        return this;
    }

    public WordOperator removeDuplicates()
    {
        words = words
            .stream()
            .distinct()
            .collect(toList());
        return this;
    }
}
