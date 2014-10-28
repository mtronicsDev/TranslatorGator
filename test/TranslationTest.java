import com.mtronicsdev.translatorGator.Translator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author mtronics_dev (Maxi Schmeller)
 * @version 28.10.2014 19:50
 */
public class TranslationTest {

    @Test
    public void testTranslation() {
        Translator.initialize();
        Translator.setLocale("de_DE");

        assertEquals("Test Auto Fertig", Translator.translate("%test% %car% %%%%%%%%%%%%%%%%%%%pointer1%%%%%%%%%%%%%%%%%%%"));
        assertEquals("9.755.345.187.399,57 \u20ac", Translator.translate("%9,755,345,187,399.5656C%")); //€-Sign
        assertTrue(Translator.translate("%date% %time%").matches("\\d{1,2}(\\.)\\d{1,2}(\\.)\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}"));

        Translator.setLocale("en_US");

        assertEquals("test car finished", Translator.translate("%test% %car% %%%%%%%%%%%%%%%%%%%pointer1%%%%%%%%%%%%%%%%%%%"));
        assertEquals("$9,755,345,187,399.57", Translator.translate("%9,755,345,187,399.5656C%"));
        assertTrue(Translator.translate("%date% %time%").matches("\\p{Alpha}{3} \\d{1,2}, \\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2} [A|P]M"));
    }

}
