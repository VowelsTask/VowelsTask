package com.vowelstask.unittest.logic;

import java.io.IOException;
import java.io.StringReader;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.vowelstask.io.PropertiesLoader.DEFAULT_VOWELS;
import static com.vowelstask.io.PropertiesLoader.PROPERTIES;
import static com.vowelstask.io.PropertiesLoader.VOWELS;
import static com.vowelstask.io.PropertiesLoader.loadProperties;
import static com.vowelstask.unittest.helpers.CalculatorAndFormatterTestingHelper.runCalculateAverageTest;

class AverageCalculatorTest
{
    @BeforeEach
    void clearAndLoadPropertiesBeforeEachTest() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(VOWELS + "=" + DEFAULT_VOWELS));
    }

    @AfterAll
    static void clearPropertiesAfterLastTest()
    {
        PROPERTIES.clear();
    }

    @Test
    void shouldReturnEmptyMapIfNoWordsGiven()
    {
        runCalculateAverageTest(emptyList());
    }

    @Test
    void shouldCalculateZeroAverageIfNoVowelsFound()
    {
        runCalculateAverageTest(asList("qw", "r9p", "?SD.", "", "", "..", "81"),
            "[], 4", "0",
            "[], 3", "0",
            "[], 2", "0",
            "[], 0", "0");
    }

    @Test
    void shouldSortUniqueVowelsAlphabetically()
    {
        runCalculateAverageTest(asList("ueoyiaDa", "ayoGiuez"),
            "[a, e, i, o, u, y], 8", "6.5");
    }

    @Test
    void shouldSortResultsFromLongestToShortestWord()
    {
        runCalculateAverageTest(asList("4oe?", "2u", "asdf6o", "y2", "4Li?", "oo3", "SU;-gi11wrt", "iiii"),
            "[i], 11", "1",
            "[a, o], 6", "2",
            "[i], 4", "2.5",
            "[e, o], 4", "2",
            "[o], 3", "2",
            "[y], 2", "1",
            "[u], 2", "1");
    }

    @Test
    void shouldCalculateDefaultVowelsAverageNumberPerVowelsSetAndWordLength()
    {
        runCalculateAverageTest(
            asList("Urt", "colab.!", "kot?ek", "-asd", "mum", "dadada", "aaaabb", "platon", "bamboo", "platon",
                "bamboo"),
            "[a, o], 7", "2",
            "[e, o], 6", "2",
            "[a], 6", "3.5",
            "[a, o], 6", "2.5",
            "[a], 4", "1",
            "[u], 3", "1",
            "[], 3", "0");
    }

    @Test
    void shouldCalculateCustomVowelsAverageNumberPerVowelsSetAndWordLength() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(VOWELS + "=" + "eO"));
        runCalculateAverageTest(
            asList("Urt", "colab.!", "kot?ek", "-asd", "mum", "dadada", "aaaabb", "platon", "bamboo", "bamooo", "ee"),
            "[o], 7", "1",
            "[e, o], 6", "2",
            "[], 6", "0",
            "[o], 6", "2",
            "[], 4", "0",
            "[], 3", "0",
            "[e], 2", "2");
    }
}
