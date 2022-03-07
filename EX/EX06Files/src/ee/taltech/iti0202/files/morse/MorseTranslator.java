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
            toMorseCode.put(part1.toLowerCase(Locale.ROOT), part2);
            fromMorseCode.put(part2, part1.toLowerCase(Locale.ROOT));
        }
        return toMorseCode;
    }

    private String translateLineToMorse(String line) {
        StringBuilder res = new StringBuilder(" ");
        String lowercase = line.toLowerCase(Locale.ROOT);
        for (int i = 0; i < line.length(); i++) {
            res.append(toMorseCode.getOrDefault(Character.toString(lowercase.charAt(i)), "  ")).append(" ");
        }
        return res.substring(1, res.length() - 1);
    }

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
