package fr.univ_lyon1.info.m1.elizagpt.model.message;

/**
 * This class represents the state of the user's name.
 * It provides methods to retrieve and set the name.
 */
public final class NameState {
    private static String name = null;

    private NameState() {
    }

    /**
     * Retrieves the current name.
     *
     * @return The current name.
     */
    public static synchronized String getName() {
        return name;
    }

    /**
     * Sets the name state to the specified new name.
     *
     * @param newName The new name to be set.
     */
    public static synchronized void setName(final String newName) {
        name = newName;
    }
}
