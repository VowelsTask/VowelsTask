package com.vowelstask.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;
import static com.vowelstask.io.PropertiesLoader.*;

public class OutputFormatter
{
    public static String convertMapToString(Map<String, Double> vowelsAverageNumberPerVowelsSetAndWordLengthMap)
    {
        StringBuilder fileContentBuilder = new StringBuilder();

        vowelsAverageNumberPerVowelsSetAndWordLengthMap
            .forEach(
                (vowelsSetAndWordLength, vowelsAverageNumber) ->
                    fileContentBuilder
                        .append("(")
                        .append(vowelsSetAndWordLength)
                        .append(") -> ")
                        .append(formatAverage(vowelsAverageNumber))
                        .append(System.lineSeparator())
            );
        return fileContentBuilder
            .toString()
            .replaceFirst(System.lineSeparator() + "$", EMPTY_STRING)
            .replaceAll("\\[", "{")
            .replaceAll("]", "}");
    }

    private static BigDecimal formatAverage(Double average)
    {
        return BigDecimal.valueOf(average)
            .setScale(Integer.valueOf(getProperty(AVERAGE_PRECISION)),
                RoundingMode.valueOf(getProperty(AVERAGE_ROUNDING_MODE)));
    }
}
