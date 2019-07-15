package com.vowelstask.endtoendtest;

import java.io.IOException;
import java.util.List;

import static java.io.File.separator;
import static java.nio.file.Files.deleteIfExists;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.test.appender.ListAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.vowelstask.Application.CANNOT_OPEN;
import static com.vowelstask.Application.CANNOT_SAVE;
import static com.vowelstask.Application.DEFAULT_PROPERTIES;
import static com.vowelstask.Application.MAIN_RESOURCES_PATH;
import static com.vowelstask.Application.PROBLEM_READING;
import static com.vowelstask.Application.PROPERTIES_FILE_NAME;
import static com.vowelstask.Application.runApp;
import static com.vowelstask.TestStringProvider.DUMMY_VALUE;
import static com.vowelstask.endtoendtest.ApplicationTest.OUTPUT_FILE_PATH;
import static com.vowelstask.io.PropertiesLoader.INPUT_FILE_NAME;
import static com.vowelstask.io.PropertiesLoader.OUTPUT_FILE_NAME;
import static com.vowelstask.io.PropertiesLoader.getProperty;

class LoggingTest
{
    private static final String TEST_RESOURCES_PATH = "src" + separator + "test" + separator + "resources" + separator;
    private static final String name = LoggingTest.class.getSimpleName();
    private static final LoggerContext loggerContext = new LoggerContext(name);
    private static Logger logger;
    private static ListAppender listAppender;

    @BeforeEach
    void addListAppenderBeforeEachTest()
    {
        logger = loggerContext.getLogger(name);
        logger.removeAppender(logger.getAppenders().values().iterator().next());
        logger.setLevel(Level.INFO);
        listAppender = new ListAppender(ListAppender.class.getSimpleName());
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterAll
    static void removeListAppenderAfterLastTest()
    {
        listAppender.clear();
        logger.removeAppender(listAppender);
    }

    @Test
    void shouldLogErrorWhenThereIsNoInputFile() throws IOException
    {
        String testPropertiesFileName = "BadInput.properties";
        runApp(TEST_RESOURCES_PATH + testPropertiesFileName, logger);
        String expectedLogMessage = CANNOT_OPEN + MAIN_RESOURCES_PATH + getProperty(INPUT_FILE_NAME);

        runLoggingTest(Level.ERROR, expectedLogMessage);
    }

    @Test
    void shouldLogErrorWhenCannotSaveFile() throws IOException
    {
        String testPropertiesFileName = "EmptyOutput.properties";
        runApp(TEST_RESOURCES_PATH + testPropertiesFileName, logger);
        String expectedLogMessage = CANNOT_SAVE + MAIN_RESOURCES_PATH + getProperty(OUTPUT_FILE_NAME);

        runLoggingTest(Level.ERROR, expectedLogMessage);
    }

    @Test
    void shouldLogWarningWhenCannotLoadPropertiesFromFile() throws IOException
    {
        runApp(DUMMY_VALUE, logger);
        String expectedLogMessage = PROBLEM_READING + PROPERTIES_FILE_NAME + DEFAULT_PROPERTIES;

        runLoggingTest(Level.WARN, expectedLogMessage);
        deleteIfExists(OUTPUT_FILE_PATH);
    }

    private static void runLoggingTest(Level expectedLogLevel, String expectedLogMessage)
    {
        List<LogEvent> events = listAppender.getEvents();

        assertEquals(1, events.size());

        LogEvent logEvent = events.iterator().next();

        assertEquals(name, logEvent.getLoggerName());
        assertEquals(expectedLogLevel, logEvent.getLevel());
        assertEquals(expectedLogMessage, logEvent.getMessage().getFormattedMessage());
    }
}
