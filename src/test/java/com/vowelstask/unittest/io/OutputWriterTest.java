package com.vowelstask.unittest.io;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vowelstask.TestStringProvider.*;
import static com.vowelstask.io.OutputWriter.writeContentToOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputWriterTest
{
    @Test
    void shouldBeAbleToWriteEmptyContent()
    {
        runOutputWriterTest(EMPTY_STRING);
    }

    @Test
    void shouldBeAbleToWriteWhitespaces()
    {
        runOutputWriterTest(ONLY_WHITESPACES_STRING);
    }

    @Test
    void shouldBeAbleToWriteMixedContent()
    {
        runOutputWriterTest(WORDS_PUNCTUATION_WHITESPACES_STRING);
    }

    private void runOutputWriterTest(String expectedContent)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriterString = new PrintWriter(stringWriter);

        writeContentToOutput(printWriterString, expectedContent);
        String actualContent = stringWriter.toString();

        assertEquals(expectedContent, actualContent);
    }
}
