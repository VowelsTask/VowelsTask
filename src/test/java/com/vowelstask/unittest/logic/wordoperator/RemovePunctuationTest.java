package com.vowelstask.unittest.logic.wordoperator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static com.vowelstask.io.PropertiesLoader.*;
import static com.vowelstask.unittest.helpers.WordOperatorTestingHelper.runRemovePunctuationTest;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

class RemovePunctuationTest
{
    private static List<String> givenWords;
    private static List<String> expectedWords;

    @BeforeEach
    void clearAndLoadPropertiesBeforeEachTest() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(PUNCTUATION_CHARACTERS + "=\\" + DEFAULT_PUNCTUATION_CHARACTERS));
    }

    @AfterAll
    static void clearPropertiesAfterLastTest()
    {
        PROPERTIES.clear();
    }

    @Test
    void shouldReturnEmptyListIfNoWordsGiven()
    {
        givenWords = emptyList();
        expectedWords = emptyList();
        runRemovePunctuationTest(givenWords, expectedWords);
    }

    @Test
    void shouldReturnInputIfNoPunctuationFound()
    {
        givenWords = asList("a", "A", "8", "hj", "WLP", "1698", "cBD5");
        expectedWords = givenWords;
        runRemovePunctuationTest(givenWords, expectedWords);
    }

    @Test
    void shouldRemoveEmptyWords()
    {
        givenWords = asList("a", "", ".,?");
        expectedWords = singletonList("a");
        runRemovePunctuationTest(givenWords, expectedWords);
    }

    @Test
    void shouldRemoveDefaultPunctuationIfFound()
    {
        givenWords = asList("a", "b.", ",b,", "cf!", ";GF", "1-2", "E!qwert-yuipasd,.fghjkzxcvb?nm");
        expectedWords = asList("a", "b", "b", "cf", "GF", "12", "Eqwertyuipasdfghjkzxcvbnm");
        runRemovePunctuationTest(givenWords, expectedWords);
    }

    @Test
    void shouldRemoveCustomPunctuationIfFound() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(PUNCTUATION_CHARACTERS + "=" + "[\\\\.\\\\?]"));
        givenWords = asList("a", "b.", ",b,", "c?f!", ";GF", "1-2", "E!qwert-yuipasd,.fghjkzxcvb?nm");
        expectedWords = asList("a", "b", ",b,", "cf!", ";GF", "1-2", "E!qwert-yuipasd,fghjkzxcvbnm");
        runRemovePunctuationTest(givenWords, expectedWords);
    }
}
