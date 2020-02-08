import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class ReadAndCompilePatterns {
    private String filename = "";
    private List<String> dictionary;
    private List<Pattern> patterns;
    private String prefix_regex = "";
    private String suffix_regex = "";

    public ReadAndCompilePatterns(String filename, String prefix_regex, String suffix_regex) {
        this.filename = filename;
        this.prefix_regex = prefix_regex;
        this.suffix_regex = suffix_regex;
    }

    public ReadAndCompilePatterns(String filename) {
        this.filename = filename;
    }

    public ReadAndCompilePatterns readFile() throws IOException {
        System.out.println("Reading " + filename + "...");
        long startTime, endTime;
        startTime = System.nanoTime();

        BufferedReader reader;

        dictionary = new ArrayList<>();

        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            // read next line
            line = line.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
            line = line.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");

            if(!line.equals("")) {
                dictionary.add(line);
            }

            line = reader.readLine();
        }

        reader.close();

        endTime = System.nanoTime();
        System.err.println("[Read "+filename+"] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public ReadAndCompilePatterns compilePatterns() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : dictionary) {
            patterns.add(Pattern.compile(prefix_regex + d + suffix_regex));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public ReadAndCompilePatterns compilePatternsInsensitive() throws IOException {
        System.out.println("Compiling " + filename + " Patterns...");
        long startTime, endTime;
        startTime = System.nanoTime();

        patterns = new ArrayList<>();

        for(String d : dictionary) {
            patterns.add(Pattern.compile(prefix_regex + d + suffix_regex, Pattern.CASE_INSENSITIVE));
        }

        endTime = System.nanoTime();
        System.err.println("[Compiled "+filename+" Patterns] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");

        return this;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void addPatternSensitive(String p) {
        patterns.add(Pattern.compile(p));
    }

    public void addPatternInsensitive(String p) {
        patterns.add(Pattern.compile(p, Pattern.CASE_INSENSITIVE));
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public String getPrefix_regex() {
        return prefix_regex;
    }

    public void setPrefix_regex(String prefix_regex) {
        this.prefix_regex = prefix_regex;
    }

    public String getSuffix_regex() {
        return suffix_regex;
    }

    public void setSuffix_regex(String suffix_regex) {
        this.suffix_regex = suffix_regex;
    }
}
