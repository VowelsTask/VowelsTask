package com.vowelstask.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertiesLoader
{
    public static final Properties PROPERTIES = new Properties();

    public static final String INPUT_FILE_NAME ="inputFileName";
    public static final String OUTPUT_FILE_NAME ="outputFileName";
    public static final String PUNCTUATION_CHARACTERS ="punctuationCharacters";
    public static final String VOWELS ="vowels";
    public static final String AVERAGE_PRECISION ="averagePrecision";
    public static final String AVERAGE_ROUNDING_MODE ="averageRoundingMode";

    public static final String DEFAULT_INPUT_FILE_NAME ="INPUT.TXT";
    public static final String DEFAULT_OUTPUT_FILE_NAME ="OUTPUT.TXT";
    public static final String DEFAULT_PUNCTUATION_CHARACTERS ="\\pP";
    public static final String DEFAULT_VOWELS ="AeIoUy";
    public static final String DEFAULT_AVERAGE_PRECISION ="1";
    public static final String DEFAULT_AVERAGE_ROUNDING_MODE ="HALF_UP";

    public static void loadProperties(Reader reader) throws IOException
    {
        PROPERTIES.put(INPUT_FILE_NAME, DEFAULT_INPUT_FILE_NAME);
        PROPERTIES.put(OUTPUT_FILE_NAME, DEFAULT_OUTPUT_FILE_NAME);
        PROPERTIES.put(PUNCTUATION_CHARACTERS, DEFAULT_PUNCTUATION_CHARACTERS);
        PROPERTIES.put(VOWELS, DEFAULT_VOWELS);
        PROPERTIES.put(AVERAGE_PRECISION, DEFAULT_AVERAGE_PRECISION);
        PROPERTIES.put(AVERAGE_ROUNDING_MODE, DEFAULT_AVERAGE_ROUNDING_MODE);

        if (reader != null)
            PROPERTIES.load(reader);
    }

    public static String getProperty(String property)
    {
        return PROPERTIES.getProperty(property);
    }
}
