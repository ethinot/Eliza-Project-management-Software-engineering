package fr.univ_lyon1.info.m1.elizagpt.model.utils;


import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;

import java.util.List;

/**
 * Operators for formatting Eliza's message.
 */
public final class TextUtils {
    private static final VerbsRepository VERBS_REPOSITORY =
            new VerbsRepository("conjugation/french-verbs.xml");

    private TextUtils() {
    }

    /**
     * Normalize the text: remove extra spaces, add a final dot if missing.
     *
     * @param text text to normalize.
     * @return normalized text.
     */
    public static String normalize(final String text) {
        return text.replaceAll("\\s+", " ") // remove extra spaces
                .replaceAll("^\\s+", "") // remove leading spaces
                .replaceAll("\\s+$", "") // remove trailing spaces
                .replaceAll("[^.!?:]$", "$0."); // add a final dot if missing
    }

    /**
     * Get a random string from a list of responses and format it with optional arguments.
     *
     * @param responses A list of response strings.
     * @param args      Optional arguments to be formatted into the response string.
     * @return A random response string with optional arguments formatted into it.
     */
    public static String getString(final List<String> responses, final String... args) {
        String response = responses.get(RandomUtils.nextInt(responses.size()));

        if (args.length == 2) {
            return String.format(response, args[0], args[1]);
        } else if (args.length == 1) {
            return String.format(response, args[0]);
        }

        return response;
    }

    /**
     * Compare two strings.
     *
     * @param subString the researched substring
     * @param message   the string message
     * @return true if the substring match the message, false else
     */
    public static boolean isMatch(final String subString, final String message) {
        return message.toLowerCase().contains(subString.toLowerCase());
    }

    /**
     * Check if the message text contains the whole word.
     *
     * @param word    the word to search for
     * @param message the message text to search in
     * @return true if the whole word is found, false otherwise
     */
    public static boolean containsWholeWord(final String word, final String message) {
        String[] words = message.split("\\s+");

        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Transforms the given text from first person to second person.
     * This method first transforms the verbs in the text and then transforms possessive adjectives.
     *
     * @param text the text to be transformed
     * @return the transformed text in second person
     */
    public static String firstToSecondPerson(final String text) {
        String transformedVerbText = transformVerbs(text);
        return transformPossesiveAdj(transformedVerbText);
    }

    private static String transformPossesiveAdj(final String text) {
        return text.replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
    }

    private static String transformVerbs(final String text) {
        String transformedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (Verb v : VERBS_REPOSITORY.getVerbs()) {
            transformedText = transformedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        return transformedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ");
    }
}
