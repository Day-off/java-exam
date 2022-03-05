package ee.taltech.iti0202.files.morse;

import java.util.*;

public class MorseTranslator {

    private final Map<String, String> toMorseCode = new HashMap<>();
    private final Map<String, String> fromMorseCode = new HashMap<>();


    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String part : lines) {
            String[] res = part.split(" ");
            String part1 = res[0];
            String part2 = res[1];
            toMorseCode.put(part1, part2);
            fromMorseCode.put(part2, part1);
        }
        return toMorseCode;
    }

    private String translateLineToMorse(String line) {
        StringBuilder res = new StringBuilder(" ");
        String lowercase = line.toLowerCase(Locale.ROOT);
        for (int i = 0; i < line.length(); i++) {
            res.append(toMorseCode.getOrDefault(lowercase.charAt(i), " "));
        }
        return res.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder res = new StringBuilder(" ");
        String[] lowercase = line.toLowerCase(Locale.ROOT).split("\t");
        for (String lin : lowercase) {
            String[] letter = lin.split(" ");
            for (String cha : letter) {
                res.append(fromMorseCode.get(cha));
            }
            res.append(" ");
        }
        return res.toString();
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> res = new ArrayList<>();
        for (String line : lines) {
            res.add(translateLineToMorse(line));
        }
        return res;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> res = new ArrayList<>();
        for (String line : lines) {
            res.add(translateLineFromMorse(line));
        }
        return res;
    }
}
