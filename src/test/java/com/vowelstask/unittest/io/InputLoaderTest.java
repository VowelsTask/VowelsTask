package com.vowelstask.unittest.io;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static com.vowelstask.TestStringProvider.*;
import static com.vowelstask.io.InputLoader.loadWordsFromInput;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputLoaderTest {

    @Test
    void shouldReturnEmptyListIfEmptyInputGiven()
    {
        runInputLoaderTest(EMPTY_STRING, emptyList());
    }

    @Test
    void shouldReturnEmptyListIfInputContainsOnlyWhitespaces()
    {
        runInputLoaderTest(ONLY_WHITESPACES_STRING, emptyList());
    }

    @Test
    void shouldReturnWordsIfFoundInTheInput()
    {
        runInputLoaderTest(WORDS_PUNCTUATION_WHITESPACES_STRING, asList("Ala", "sdf", "123", "QWE", "45,", "oII?", "YU7",
            "OP!", "u8", "kl:", ",7,", ";:", "Ty78549075uhf.", ",7,", "AeIoUy"));
    }

    private void runInputLoaderTest(String input, List<String> expectedWords)
    {
        List<String> actualWords = loadWordsFromInput(new Scanner(input));
        assertEquals(expectedWords, actualWords);
    }
}
