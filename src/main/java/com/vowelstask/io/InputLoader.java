package com.vowelstask.io;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputLoader {

    public static List<String> loadWordsFromInput(Scanner scanner)
    {
        List<String> words = new LinkedList<>();

        while (scanner.hasNext())
            words.add(scanner.next());

        return words;
    }
}
