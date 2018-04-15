import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class contains some utility helper methods
 *
 * @author Jiazhi Yang (jyang436@wisc.edu)
 */
public class WordProcessor {

    /**
     * Gets a Stream of words from the filepath.
     * <p>
     * The Stream should only contain trimmed, non-empty and UPPERCASE words.
     *
     * @param filepath file path to the dictionary file
     * @return Stream<String> stream of words read from the filepath
     * @throws IOException exception resulting from accessing the filepath
     * @see <a href="http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html">java8 stream blog</a>
     */
    public static Stream<String> getWordStream(String filepath) throws IOException {
        // get file stream => remove space => remove empty line => change to upper case
        return Files.lines(Paths.get(filepath)).map(String::trim).filter(e -> !e.equals("")).map(String::toUpperCase);
    }

    /**
     * Adjacency between word1 and word2 is defined by:
     * if the difference between word1 and word2 is of
     * 1 char replacement
     * 1 char addition
     * 1 char deletion
     * then
     * word1 and word2 are adjacent
     * else
     * word1 and word2 are not adjacent
     * <p>
     * Note: if word1 is equal to word2, they are not adjacent
     *
     * @param word1 first word
     * @param word2 second word
     * @return true if word1 and word2 are adjacent else false
     */
    public static boolean isAdjacent(String word1, String word2) {
        if (word1.equals(word2))
            return false; // same string case
        if (word1.length() == word2.length()) { // 1 char replacement case
            int sum = 0;
            for (int i = 0; i < word1.length(); i++) {
                if ((word1.charAt(i) ^ word2.charAt(i)) != 0)
                    sum++;
            }
            if (sum == 1)
                return true;
        }
        if (Math.abs(word1.length() - word2.length()) == 1) { // 1 char add and delete case
            if (word1.length() < word2.length()) { // make all cases become 1 delete cases
                String temp = word1;
                word1 = word2;
                word2 = temp;
            }
            char[] word = word1.toCharArray();
            for (int i = 0; i < word1.length(); i++) { // remove a char at each position to make new
                // substring to find whether it matches word2
                String subWord = new String();
                for (int j = 0; j < word1.length(); j++) {
                    if (i != j)
                        subWord = subWord + word[j];
                }
                if (subWord.equals(word2))
                    return true;
            }
        }
        return false;
    }
}
