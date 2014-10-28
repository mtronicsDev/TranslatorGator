import com.mtronicsdev.translatorGator.Translator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        Translator.setLocale("en_US");
        assertEquals("test car finished", Translator.translate("%test% %car% %%%%%%%%%%%%%%%%%%%pointer1%%%%%%%%%%%%%%%%%%%"));
    }

}
