package fr.univ_lyon1.info.m1.elizagpt.model.grammar;


import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.RandomUtils;

import java.util.List;

/**
 * Operators for formatting Eliza's message.
 */
public final class TextUtils {

    private static final VerbsRepository verbsRepository = new VerbsRepository("vocabulary/vocabulary.xml");

    /**
     * Normalize the text: remove extra spaces, add a final dot if missing.
     *
     * @param text text to normalize.
     * @return normalized text.
     */
    public static String normalize(final String text) {
        return text.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^\\.!?:]$", "$0.");
    }

    /**
     * Turn a 1st-person sentence (Je ...) into a plural 2nd person (Vous ...).
     * The result is not capitalized to allow forming a new sentence.
     * <p>
     * TODO: does not deal with all 3rd group verbs.
     *
     * @param text The 1st-person sentence.
     * @return The 2nd-person sentence.
     */
    public static String firstToSecondPerson(final String text) {
        String processedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (Verb v : verbsRepository.getVerbs()) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        processedText = processedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ")
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
        return processedText;
    }

    public static String getString(List<String> responses, String... args) {
        String response = responses.get(RandomUtils.nextInt(responses.size()));

        if (args.length == 2) {
            return String.format(response, args[0], args[1]);
        } else if (args.length == 1) {
            return String.format(response, args[0]);
        }

        return response;
    }

}
