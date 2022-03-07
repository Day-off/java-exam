package ee.taltech.iti0202.files.morse;

import java.util.*;

public class MorseTranslator {

    private final Map<String, String> toMorseCode = new HashMap<>();
    private final Map<String, String> fromMorseCode = new HashMap<>();

    /***
     * add Morse dictionary
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String part : lines) {
            String[] res = part.split(" ");
            String part1 = res[0];
            String part2 = res[1];
            toMorseCode.put(part1.toLowerCase(Locale.ROOT), part2);
            fromMorseCode.put(part2, part1.toLowerCase(Locale.ROOT));
        }
        return toMorseCode;
    }

    /***
     * To morse one line
     */
    private String translateLineToMorse(String line) {
        StringBuilder res = new StringBuilder("");
        String lowercase = line.toLowerCase(Locale.ROOT);
        for (int i = 0; i < line.length(); i++) {
            if (toMorseCode.containsKey(Character.toString(lowercase.charAt(i)))) {
                res.append(toMorseCode.get(Character.toString(lowercase.charAt(i))));
                if (i + 1 < line.length()) {
                    if (toMorseCode.containsKey(Character.toString(lowercase.charAt(i + 1)))) {
                        res.append(" ");
                    }
                }
            } else {
                res.append("\t");
            }
        }
        return res.toString();
    }

    /***
     * From morse one line
     */
    private String translateLineFromMorse(String line) {
        StringBuilder res = new StringBuilder("");
        String[] lowercase = line.toLowerCase(Locale.ROOT).split("\t");
        for (String lin : lowercase) {
            String[] letter = lin.split(" ");
            for (String cha : letter) {
                res.append(fromMorseCode.get(cha.toLowerCase(Locale.ROOT)));
            }
            res.append(" ");
        }
        return res.substring(0, res.length() - 1);
    }

    /***
     * To morse lines
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> res = new ArrayList<>();
        for (String line : lines) {
            res.add(translateLineToMorse(line));
        }
        return res;
    }

    /***
     * From morse lines
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> res = new ArrayList<>();
        for (String line : lines) {
            res.add(translateLineFromMorse(line));
        }
        return res;
    }
}
