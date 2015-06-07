package hrytsenko;

import static hrytsenko.Filenames.generateUniqueName;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link Filenames}.
 */
public class FilenamesTest {

    @Test
    public void testNoDuplicates() {
        doTest("logo.png", "logo.png", "background.png");
    }

    @Test
    public void testOneDuplicate() {
        doTest("logo (1).png", "logo.png", "background.png", "logo.png");
    }

    @Test
    public void testSeveralDuplicates() {
        doTest("logo (3).png", "logo.png", "background.png", "logo.png", "logo (2).png");
    }

    @Test
    public void testDifferentExtensions() {
        doTest("logo.png", "logo.png", "logo.jpg");
    }

    private void doTest(String expectedName, String originalName, String... knownNames) {
        String generatedName = generateUniqueName(originalName, asList(knownNames));
        assertEquals(expectedName, generatedName);
    }

}
