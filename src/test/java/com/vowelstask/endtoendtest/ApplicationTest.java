package com.vowelstask.endtoendtest;

import com.vowelstask.Application;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.nio.file.Path;

import static com.vowelstask.Application.MAIN_RESOURCES_PATH;
import static com.vowelstask.TestStringProvider.*;
import static com.vowelstask.io.PropertiesLoader.DEFAULT_OUTPUT_FILE_NAME;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest
{
    public static final Path OUTPUT_FILE_PATH = get(MAIN_RESOURCES_PATH + DEFAULT_OUTPUT_FILE_NAME);

    @BeforeEach
    void deleteOutputFileBeforeEachTest() throws IOException
    {
        deleteIfExists(OUTPUT_FILE_PATH);
    }

    @AfterAll
    static void deleteOutputFileAfterLastTest() throws IOException
    {
        deleteIfExists(OUTPUT_FILE_PATH);
    }

    @Test
    void shouldGenerateCorrectOutputFile() throws Throwable
    {
        runApplicationTest(EXPECTED_OUTPUT_FILE_PROPERTIES,
            () -> Application.main(new String[]{}));
    }

    @Test
    void shouldUseDefaultPropertiesWhenCannotLoadThemFromFile() throws Throwable
    {
        String name = ApplicationTest.class.getSimpleName();
        Logger logger = new LoggerContext(name).getLogger(name);
        logger.removeAppender(logger.getAppenders().values().iterator().next());

        runApplicationTest(EXPECTED_OUTPUT_DEFAULT_PROPERTIES,
            () -> Application.runApp(DUMMY_VALUE, logger));
    }

    private void runApplicationTest(String expectedOutput, Executable function) throws Throwable
    {
        function.execute();
        String actualOutput = new String(readAllBytes(OUTPUT_FILE_PATH));

        assertEquals(expectedOutput, actualOutput);
    }
}
