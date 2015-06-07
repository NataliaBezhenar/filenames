package hrytsenko;

import java.util.List;

/**
 * Utilities for working with filenames.
 */
public final class Filenames {

    /**
     * Generates the unique name to avoid duplication with the known names.
     * 
     * <p>
     * To generate unique name, we add serial number to the end of name.
     * 
     * For example: "logo.png", "logo (1).png" and so on.
     * 
     * @param originalName
     *            the original name.
     * @param knownNames
     *            the list of known names.
     * 
     * @return the generated unique name.
     */
    public static String generateUniqueName(String originalName, List<String> knownNames) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    private Filenames() {
    }

}
