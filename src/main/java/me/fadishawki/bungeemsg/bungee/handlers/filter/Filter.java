package me.fadishawki.bungeemsg.bungee.handlers.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Filter {

    private static char[] characters = {' ', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', ',', '.', '/', ':', '\'', '{', '}', '-', '=', '<', '>', '?', ';', '"', '[', ']', '_', '+', '~', '`'};

    private HashMap<String, List<String>> bannedWords;

    private Filter(HashMap<String, List<String>> bannedWords) {
        this.bannedWords = bannedWords;
    }

    public boolean filter(String message) {
        boolean modified = false;
        for (String word : bannedWords.keySet()) {
            if (message.contains(word)) {
                message = message.replaceAll(word, getReplacement(word));
                modified = true;
            }
            List<String> variations = new ArrayList<>();
            variations.addAll(checkAlias(word, message));
            variations.addAll(checkVariations(word, message));
            for (String variation : variations) {
                message = message.replaceAll(variation, getReplacement(variation));
                modified = true;
            }
        }
        return modified;
    }

    private String getReplacement(String word) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            s.append("*");
        }
        return s.toString();
    }

    private List<String> checkAlias(String word, String message) {
        List<String> aliases = bannedWords.get(word);
        List<String> variations = new ArrayList<>();
        for (String alias : aliases) {
            if (message.contains(alias)) {
                variations.add(alias);
                variations.addAll(checkVariations(alias, message));
            }
        }
        return variations;
    }

    private List<String> checkVariations(String word, String message) {
        List<String> variations = new ArrayList<>();
        StringBuilder b = new StringBuilder(word);
        int length = 0;
        for (char character : characters) {
            while (length < word.length()) {
                b.deleteCharAt(length);
                b.insert(length, character);
                if (message.contains(b.toString())) {
                    variations.add(b.toString());
                }
                b = new StringBuilder(word);
                length++;
            }
            length = 0;
        }
        return variations;
    }
}
