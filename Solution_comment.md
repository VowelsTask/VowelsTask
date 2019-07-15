1. There are 32 Junit5 tests running quickly in memory (~140ms on my machine).
2. There was no need to use mocking libraries because input/output file can be simulated by String thanks to Scanner/PrintWriter capabilities.
Testing the generation of WARN/ERORR logs was possible to be done in memory thanks to log4j2 ListAppender class.
3. There are 5 end to end tests reading/writing from/to disk (input/output/properties files), so they are a bit slower (~1.1s on my machine).
4. The code coverage by above 37 tests is 100% (classes, methods and lines).
5. One of the unit tests (shouldOverwriteUserValueWithDefaultIfWrongPropertyValueLoaded) is red. It is conscious decision to keep it red to remind that the implementation of the task has the following weak point:
The user can set e.g. the property *averageRoundingMode* to a dummy or empty value and break the program (IllegalArgumentException). It should be handled - default value HALF_UP should be set in such case.
6. *ApplicationTest* class contains two tests where a function is given as a parameter to another function - this was implemented using Java 8 lambda expressions and Junit5 Functional Interface called *Executable*.
7. The program was written to work on any of the following operating systems:
Linux/Unix, Windows, Mac.
It was possible thanks to java.io.File.separator and java.lang.System.lineSeparator.
The program was tested successfully on Windows and Linux.
