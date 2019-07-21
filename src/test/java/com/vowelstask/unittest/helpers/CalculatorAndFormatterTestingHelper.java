package com.vowelstask.unittest.helpers;

import com.vowelstask.logic.AverageCalculator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.vowelstask.logic.OutputFormatter.convertMapToString;
import static java.lang.Double.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorAndFormatterTestingHelper
{
    public static void runCalculateAverageTest(List<String> givenWords, String... expectedMapElements)
    {
        Map<String, Double> actualResultMap = new AverageCalculator()
            .calculateVowelsAverageNumberPerVowelsSetAndWordLength(givenWords);

        Map<String, Double> expectedResultMap = buildStringDoubleMap(expectedMapElements);

        assertEquals(expectedResultMap, actualResultMap);
        assertEquals(extractWordLengths(expectedResultMap), extractWordLengths(actualResultMap));
    }

    public static void runOutputFormatterTest(String expectedResult, String... givenMapElements)
    {
        Map<String, Double> map = buildStringDoubleMap(givenMapElements);
        String actualResult = convertMapToString(map);
        assertEquals(expectedResult, actualResult);
    }

    private static Map<String, Double> buildStringDoubleMap(String... expectedMapElements)
    {
        Iterator<String> listIterator = asList(expectedMapElements).iterator();
        Map<String, Double> map = new LinkedHashMap<>();

        while (listIterator.hasNext())
        {
            String key = listIterator.next();
            Double value = valueOf(listIterator.next());
            map.put(key, value);
        }
        return map;
    }

    private static String extractWordLengths(Map<String, Double> resultMap)
    {
        return resultMap
            .keySet()
            .stream()
            .map(key -> key.substring(key.lastIndexOf("], ") + 3))
            .collect(toList())
            .toString();
    }
}
