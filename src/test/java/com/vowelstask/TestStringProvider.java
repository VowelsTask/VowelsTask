package com.vowelstask;

import static java.lang.System.lineSeparator;

public class TestStringProvider
{
    public static final String EMPTY_STRING = "";
    public static final String DUMMY_PROPERTY = "dummyProperty";
    public static final String DUMMY_VALUE = "=dummyValue";
    public static final String ONLY_WHITESPACES_STRING = "  \t\t  \t \n  \r\n";

    public static final String NOT_YET_IMPLEMENTED_MESSAGE = "Overwriting wrong property value set by user with " +
        "default value is not yet implemented";

    public static final String PROPERTIES_SUBSET = "#no difference if vowels are listed in capital or small letters\n" +
        "vowels=Iu\n" +
        "#rounding mode used when saving average to output file, allowed values: UP, DOWN, CEILING, FLOOR, HALF_UP, HALF_DOWN, HALF_EVEN\n" +
        "averageRoundingMode=CEILING";

    public static final String ALL_PROPERTIES = "inputFileName=INPUT2.TXT\n"+
        "outputFileName=OUTPUT3.TXT\n"+
        "punctuationCharacters=[\\\\.\\\\?]\n"+
        "vowels=y\n"+
        "averagePrecision=1\n"+
        "averageRoundingMode=DOWN";

    public static final String WORDS_PUNCTUATION_WHITESPACES_STRING = "Ala sdf 123\n" +
        "QWE\n" +
        "\n" +
        " 45,\t   oII?\n" +
        "YU7\n" +
        "OP! u8 kl: ,7, ;:\n" +
        "Ty78549075uhf.\n" +
        "\n" +
        ",7,\n" +
        "AeIoUy";

    public static final String EXPECTED_OUTPUT_FILE_PROPERTIES =
        "({a, e, i, o, u, y}, 7) -> 6.00" + lineSeparator() +
        "({o}, 6) -> 2.00" + lineSeparator() +
        "({a, o}, 6) -> 3.67" + lineSeparator() +
        "({a}, 6) -> 3.00" + lineSeparator() +
        "({a}, 5) -> 2.00" + lineSeparator() +
        "({a, o}, 5) -> 2.00" + lineSeparator() +
        "({e, i}, 4) -> 2.00" + lineSeparator() +
        "({a, e}, 4) -> 2.00" + lineSeparator() +
        "({a}, 3) -> 2.00" + lineSeparator() +
        "({}, 3) -> 0.00" + lineSeparator() +
        "({o}, 3) -> 1.50";

    public static final String EXPECTED_OUTPUT_DEFAULT_PROPERTIES =
        "({a, e, i, o, u, y}, 7) -> 6.0" + lineSeparator() +
        "({o}, 6) -> 2.0" + lineSeparator() +
        "({a, o}, 6) -> 3.7" + lineSeparator() +
        "({a}, 6) -> 3.0" + lineSeparator() +
        "({a}, 5) -> 2.0" + lineSeparator() +
        "({a, o}, 5) -> 2.0" + lineSeparator() +
        "({e, i}, 4) -> 2.0" + lineSeparator() +
        "({a, e}, 4) -> 2.0" + lineSeparator() +
        "({a}, 3) -> 2.0" + lineSeparator() +
        "({}, 3) -> 0.0" + lineSeparator() +
        "({o}, 3) -> 1.5";
}
