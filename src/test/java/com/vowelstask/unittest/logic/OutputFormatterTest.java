package com.vowelstask.unittest.logic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static com.vowelstask.TestStringProvider.EMPTY_STRING;
import static com.vowelstask.io.PropertiesLoader.*;
import static com.vowelstask.unittest.helpers.CalculatorAndFormatterTestingHelper.runOutputFormatterTest;
import static java.lang.System.lineSeparator;

class OutputFormatterTest
{
    @BeforeEach
    void clearAndLoadPropertiesBeforeEachTest() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(AVERAGE_PRECISION + "=" + DEFAULT_AVERAGE_PRECISION + lineSeparator() +
            AVERAGE_ROUNDING_MODE + "=" + DEFAULT_AVERAGE_ROUNDING_MODE));
    }

    @AfterAll
    static void clearPropertiesAfterLastTest()
    {
        PROPERTIES.clear();
    }

    @Test
    void shouldReturnEmptyStringIfEmptyMapGiven()
    {
        runOutputFormatterTest(EMPTY_STRING);
    }

    @Test
    void shouldConvertMapToString()
    {
        String expectedResult = "({a, u}, 8) -> 4.0" + lineSeparator()
            + "({}, 7) -> 0.0" + lineSeparator()
            + "({e}, 7) -> 1.7" + lineSeparator()
            + "({i}, 5) -> 0.4" + lineSeparator()
            + "({a, o, y}, 20) -> 2.0" + lineSeparator()
            + "({}, 0) -> 0.0";

        runOutputFormatterTest(expectedResult, "[a, u], 8", "3.99999999",
            "[], 7", "0",
            "[e], 7", "1.650",
            "[i], 5", "0.449",
            "[a, o, y], 20", "1.95",
            "[], 0", "0");
    }

    @Test
    void shouldConvertMapToStringWithCustomRoundingRules() throws IOException
    {
        PROPERTIES.clear();
        loadProperties(new StringReader(AVERAGE_PRECISION + "=" + "0" + lineSeparator() +
            AVERAGE_ROUNDING_MODE + "=" + "FLOOR"));

        String expectedResult = "({a, u}, 8) -> 3" + lineSeparator()
            + "({}, 7) -> 0" + lineSeparator()
            + "({e}, 7) -> 1" + lineSeparator()
            + "({i}, 5) -> 0" + lineSeparator()
            + "({a, o, y}, 20) -> 3" + lineSeparator()
            + "({}, 0) -> 0";

        runOutputFormatterTest(expectedResult, "[a, u], 8", "3.99999999",
            "[], 7", "0",
            "[e], 7", "1.6650",
            "[i], 5", "0.4949",
            "[a, o, y], 20", "3.995",
            "[], 0", "0");
    }
}
