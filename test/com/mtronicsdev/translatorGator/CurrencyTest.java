package com.mtronicsdev.translatorGator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mtronics_dev (Maxi Schmeller)
 * @version 28.10.2014 21:04
 */
public class CurrencyTest {

    @Test
    public void testCurrency() {
        Translator.initialize();

        Translator.setLocale("de_DE");
        assertEquals("9.755.345.187.399,57 \u20ac", Translator.translate("%9,755,345,187,399.5656C%")); //€-Sign

        Translator.setLocale("en_US");
        assertEquals("$9,755,345,187,399.57", Translator.translate("%9,755,345,187,399.5656C%"));
    }

}
