package com.vowelstask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static java.io.File.separator;
import static java.nio.file.Paths.get;

import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.vowelstask.logic.AverageCalculator;
import com.vowelstask.logic.WordOperator;

import static com.vowelstask.io.InputLoader.loadWordsFromInput;
import static com.vowelstask.io.OutputWriter.writeContentToOutput;
import static com.vowelstask.io.PropertiesLoader.INPUT_FILE_NAME;
import static com.vowelstask.io.PropertiesLoader.OUTPUT_FILE_NAME;
import static com.vowelstask.io.PropertiesLoader.getProperty;
import static com.vowelstask.io.PropertiesLoader.loadProperties;
import static com.vowelstask.logic.OutputFormatter.convertMapToString;

public class Application
{
    public static final String PROBLEM_READING = "Problem reading ";
    public static final String DEFAULT_PROPERTIES = ". Default properties will be used.";
    public static final String CANNOT_SAVE = "Cannot save file: ";
    public static final String CANNOT_OPEN = "Cannot open file: ";
    public static final String MAIN_RESOURCES_PATH = "src" + separator + "main" + separator + "resources" + separator;
    public static final String PROPERTIES_FILE_NAME = "VowelsTask.properties";

    public static void main(String[] args) throws IOException
    {
        runApp(MAIN_RESOURCES_PATH + PROPERTIES_FILE_NAME, getLogger(Application.class));
    }

    public static void runApp(String propertiesPathAndFileName, Logger logger) throws IOException {
        Reader propertiesReader = null;
        try {
            propertiesReader = new FileReader(propertiesPathAndFileName);
        }
        catch (FileNotFoundException canBeIgnoredBecauseDefaultPropertiesWillBeUsed)
        {
            logger.warn(PROBLEM_READING + PROPERTIES_FILE_NAME + DEFAULT_PROPERTIES);
        }
        loadProperties(propertiesReader);

        Path inputFilePathAndName = get(MAIN_RESOURCES_PATH + getProperty(INPUT_FILE_NAME));
        String outputFilePathAndName = MAIN_RESOURCES_PATH + getProperty(OUTPUT_FILE_NAME);

        try {
            Scanner fileScanner = new Scanner(inputFilePathAndName);
            WordOperator wordOperator = new WordOperator(loadWordsFromInput(fileScanner));

            List<String> words = wordOperator
                    .removePunctuation()
                    .convertToLowerCase()
                    .removeDuplicates()
                    .getWords();

            PrintWriter printWriterFile = new PrintWriter(new File(outputFilePathAndName));
            String content = convertMapToString(new AverageCalculator()
                    .calculateVowelsAverageNumberPerVowelsSetAndWordLength(words));
            writeContentToOutput(printWriterFile, content);
        }
        catch (FileNotFoundException e) {
            logger.error(CANNOT_SAVE + outputFilePathAndName);
        }
        catch (IOException e) {
            logger.error(CANNOT_OPEN + inputFilePathAndName.toString());
        }
    }
}
