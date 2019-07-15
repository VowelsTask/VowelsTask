package com.vowelstask.unittest.logic.wordoperator;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import org.junit.jupiter.api.Test;

import static com.vowelstask.unittest.helpers.WordOperatorTestingHelper.runConvertToLowerCaseTest;

class ConvertToLowerCaseTest
{
    private static List<String> givenWords;
    private static List<String> expectedWords;

    @Test
    void shouldReturnEmptyListIfNoWordsGiven()
    {
        givenWords = emptyList();
        expectedWords = emptyList();
        runConvertToLowerCaseTest(givenWords, expectedWords);
    }

    @Test
    void shouldReturnInputIfNoUpperCaseFound()
    {
        givenWords = asList("a", ".", "7", "aser", "?!", "450", "g:8", "");
        expectedWords = givenWords;
        runConvertToLowerCaseTest(givenWords, expectedWords);
    }

    @Test
    void shouldConvertToLowerCaseIfUpperCaseFound()
    {
        givenWords = asList("a", "b.", "B.", "!?.", "98", "QWERTY", "K2?qwertyuipasdfghjklzxcvbnm", "");
        expectedWords = asList("a", "b.", "b.", "!?.", "98", "qwerty", "k2?qwertyuipasdfghjklzxcvbnm", "");
        runConvertToLowerCaseTest(givenWords, expectedWords);
    }
}
