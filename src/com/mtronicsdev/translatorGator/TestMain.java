package com.mtronicsdev.translatorGator;

/**
 * @author mtronics_dev (Maxi Schmeller)
 * @version 21.10.2014 22:13
 */
public class TestMain {
    public static void main(String[] args) {
        Translator.initialize();
        System.out.println(Translator.translate("%test% %car% %%%%%%%%%%%%%%%%%%%pointer1%%%%%%%%%%%%%%%%%%%"));
        System.out.println(Translator.translate("%9,755,345,187,399.5656C%"));
        System.out.println(Translator.translate("%date% %time%"));
    }
}
