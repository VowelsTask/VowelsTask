package com.vowelstask.unittest.io;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static com.vowelstask.TestStringProvider.*;
import static com.vowelstask.io.PropertiesLoader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesLoaderTest
{
    @BeforeEach
    void clearPropertiesBeforeEachTest()
    {
        PROPERTIES.clear();
    }

    @AfterAll
    static void clearPropertiesAfterLastTest()
    {
        PROPERTIES.clear();
    }

    @Test
    void shouldLoadDefaultPropertiesIfThereIsNoFile() throws IOException
    {
        loadProperties(null);
        verifyProperties(6, DEFAULT_INPUT_FILE_NAME, DEFAULT_OUTPUT_FILE_NAME,
            DEFAULT_PUNCTUATION_CHARACTERS, DEFAULT_VOWELS, DEFAULT_AVERAGE_PRECISION, DEFAULT_AVERAGE_ROUNDING_MODE);
    }

    @Test
    void shouldLoadDefaultPropertiesIfEmptyFileGiven() throws IOException
    {
        loadProperties(new StringReader(EMPTY_STRING));
        verifyProperties(6, DEFAULT_INPUT_FILE_NAME, DEFAULT_OUTPUT_FILE_NAME,
            DEFAULT_PUNCTUATION_CHARACTERS, DEFAULT_VOWELS, DEFAULT_AVERAGE_PRECISION, DEFAULT_AVERAGE_ROUNDING_MODE);
    }

    @Test
    void shouldOverwriteSubsetOfPropertiesIfGiven() throws IOException
    {
        loadProperties(new StringReader(PROPERTIES_SUBSET));
        verifyProperties(6, DEFAULT_INPUT_FILE_NAME, DEFAULT_OUTPUT_FILE_NAME,
            DEFAULT_PUNCTUATION_CHARACTERS,"Iu", DEFAULT_AVERAGE_PRECISION,
            "CEILING");
    }

    @Test
    void shouldOverwriteAllPropertiesIfGiven() throws IOException
    {
        loadProperties(new StringReader(ALL_PROPERTIES));
        verifyProperties(6,"INPUT2.TXT",
            "OUTPUT3.TXT", "[\\.\\?]", "y",
            "1", "DOWN");
    }

    @Test
    void shouldNotAffectDefaultPropertiesWhenWrongAdditionalPropertyLoaded() throws IOException
    {
        loadProperties(new StringReader(DUMMY_PROPERTY + DUMMY_VALUE));
        verifyProperties(7, DEFAULT_INPUT_FILE_NAME, DEFAULT_OUTPUT_FILE_NAME,
            DEFAULT_PUNCTUATION_CHARACTERS, DEFAULT_VOWELS, DEFAULT_AVERAGE_PRECISION, DEFAULT_AVERAGE_ROUNDING_MODE);
    }

    @Test
    void shouldOverwriteUserValueWithDefaultIfWrongPropertyValueLoaded() throws IOException
    {
        loadProperties(new StringReader(AVERAGE_ROUNDING_MODE + DUMMY_VALUE));
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED_MESSAGE);
    }

    private void verifyProperties(int expectedNumberOfPropertiesLoaded, String expectedInputFileName,
        String expectedOutputFileName, String expectedPunctuationCharacters, String expectedVowels,
        String expectedAveragePrecision, String expectedAverageRoundingMode)
    {
        assertEquals(expectedNumberOfPropertiesLoaded, PROPERTIES.size());
        assertEquals(expectedInputFileName, getProperty(INPUT_FILE_NAME));
        assertEquals(expectedOutputFileName, getProperty(OUTPUT_FILE_NAME));
        assertEquals(expectedPunctuationCharacters, getProperty(PUNCTUATION_CHARACTERS));
        assertEquals(expectedVowels, getProperty(VOWELS));
        assertEquals(expectedAveragePrecision, getProperty(AVERAGE_PRECISION));
        assertEquals(expectedAverageRoundingMode, getProperty(AVERAGE_ROUNDING_MODE));
    }
}
