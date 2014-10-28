package com.mtronicsdev.translatorGator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author mtronics_dev (Maxi Schmeller)
 * @version 28.10.2014 21:04
 */
public class DateTimeTest {

    @Test
    public void testDateTime() {
        Translator.initialize();

        Translator.setLocale("de_DE");
        assertTrue(Translator.translate("%date% %time%").matches("\\d{1,2}(\\.)\\d{1,2}(\\.)\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}"));

        Translator.setLocale("en_US");
        assertTrue(Translator.translate("%date% %time%").matches("\\p{Alpha}{3} \\d{1,2}, \\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2} [A|P]M"));
    }

}
