package com.vowelstask.io;

import java.io.PrintWriter;

public class OutputWriter
{
    public static void writeContentToOutput(PrintWriter printWriter, String content)
    {
        printWriter.print(content);
        printWriter.close();
    }
}
