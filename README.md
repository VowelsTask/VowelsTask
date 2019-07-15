# vowelsTask
General description:
This is a Java 8 program (with JUnit tests) that:
- loads words from a file,
- determines the average number of vowels per word grouped by: set of vowels present in a word and length of the word,
- and writes the result into a file.

Example:
INPUT:
*Platon made speedy bamboo boats.*

Assuming that in the above sentence the complete list of vowels is: a, o, e, the OUTPUT should be:
OUTPUT:
*({a, o}, 6) -> 2.5
({e}, 6) -> 2.0
({a, o}, 5) -> 2.0
({a, e}, 4) -> 2.0*

---------------------------------------------------------------------------------------------------------------
Detailed description:
Given the supplied text file, write a Java program that loads all words and determines the average number of vowels per word grouped by: set of vowels present in a word and length of the word. Result should be written to the output file.

Assumptions:
1. What should be configurable (using additional file):
- input file name,
- output file name,
- punctuation characters set,
- vowels set,
- precision of the average (e.g. 2 digits after comma),
- rounding mode of the average (e.g. round half up).
2. If some of above values are not delivered, then default values should be used for them.
3. Capital and lower case letters should be treated as the same.
4. Words in the input file are separated by at least one whitespace character.
5. Input is small enough to fit in memory.
6. The unique group of vowels in the output file should be sorted alphabetically (in ascending order), i.e. {a, e, i, o, u}.
7. Duplicated words should be treated as one unique word, so for such an input file:
*Platon bamboo bamboo*
the result in the output file should be:
*({a, o}, 6) -> 2.5* calculated as (2+3)/2
It should NOT be 2.67 calculated as (2+3+3)/3
8. The lines in the output file should be sorted from longest words to the shortest ones, i.e.:
*({o}, 6) -> 2.00
({o}, 4) -> 2.00
({o}, 3) -> 2.00*
