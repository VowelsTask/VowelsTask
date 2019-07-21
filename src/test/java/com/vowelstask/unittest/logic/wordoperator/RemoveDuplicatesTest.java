package com.vowelstask.unittest.logic.wordoperator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vowelstask.unittest.helpers.WordOperatorTestingHelper.runRemoveDuplicatesTest;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

class RemoveDuplicatesTest
{
    private static List<String> givenWords;
    private static List<String> expectedWords;

    @Test
    void shouldReturnEmptyListIfNoWordsGiven()
    {
        givenWords = emptyList();
        expectedWords = emptyList();
        runRemoveDuplicatesTest(givenWords, expectedWords);
    }

    @Test
    void shouldReturnInputIfNoDuplicatesFound()
    {
        givenWords = asList("a", ".", "7", "aser", "?!", "450", "g:8", "");
        expectedWords = givenWords;
        runRemoveDuplicatesTest(givenWords, expectedWords);
    }

    @Test
    void shouldRemoveDuplicatesIfFound()
    {
        givenWords = asList("a", "b.", "98", "B.", "!?.", "98", "QWERTY", "K2?qwertyui", "", "B.");
        expectedWords = asList("a", "b.", "98", "B.", "!?.", "QWERTY", "K2?qwertyui", "");
        runRemoveDuplicatesTest(givenWords, expectedWords);
    }
}
