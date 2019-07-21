package com.vowelstask.unittest.helpers;

import com.vowelstask.logic.WordOperator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordOperatorTestingHelper
{
    public static void runRemovePunctuationTest(List<String> givenWords, List<String> expectedWords)
    {
        List<String> actualResultSet = new WordOperator(givenWords)
            .removePunctuation()
            .getWords();

        assertEquals(expectedWords, actualResultSet);
    }

    public static void runConvertToLowerCaseTest(List<String> givenWords, List<String> expectedWords)
    {
        List<String> actualResultSet = new WordOperator(givenWords)
            .convertToLowerCase()
            .getWords();

        assertEquals(expectedWords, actualResultSet);
    }

    public static void runRemoveDuplicatesTest(List<String> givenWords, List<String> expectedWords)
    {
        List<String> actualResultSet = new WordOperator(givenWords)
            .removeDuplicates()
            .getWords();

        assertEquals(expectedWords, actualResultSet);
    }
}
