package com.mtronicsdev.translatorGator;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mtronics_dev (Maxi Schmeller)
 * @version 21.10.2014 21:56
 */
public class Translator {
    private static Locale locale;
    private static String start, end; //Markers for variables

    private static Properties settings;
    private static Properties dictionary;

    private static Pattern pattern;

    public static void initialize() {
        settings = new Properties();
        dictionary = new Properties();

        try {
            settings.load(new FileInputStream("res/translatorGator/settings.properties"));
            locale = getLocale(settings.getProperty("languages.default"));
            dictionary.load(
              new FileInputStream(
                "res/translatorGator/" + locale.getLanguage() + "_" + locale.getCountry() + ".lang"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("TranslatorGator: Wrong language file?");
        }

        start = settings.getProperty("variables.markers.start");
        end   = settings.getProperty("variables.markers.end");

        pattern = Pattern.compile(start + "([^" + start + "].*?)" + end);
    }

    public static void setLocale(String locale) {
        Translator.locale = getLocale(locale);
        try {
            dictionary.load(
              new FileInputStream(
                "res/translatorGator/" + Translator.locale.getLanguage() + "_" + Translator.locale.getCountry() + ".lang"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String translate(String input) {
        int position = 0;

        while (true) {
            Matcher matcher = pattern.matcher(input);
            if(!matcher.find(position)) break;

            String group = matcher.group(1);

            if (dictionary.containsKey(group))
                input = input.replaceAll(start + group + end, dictionary.getProperty(group));
            else if (group.equals("time"))
                input = input.replaceAll(start + group + end, DateFormat.getTimeInstance(DateFormat.DEFAULT, locale)
                  .format(new Date()));
            else if (group.equals("date"))
                input = input.replaceAll(start + group + end, DateFormat.getDateInstance(DateFormat.DEFAULT, locale)
                  .format(new Date()));
            else if (group.matches("[0-9,]+\\.?[0-9]+C"))
                input = input.replaceAll(start + group + end, formatCurrency(group));
            else position = matcher.start() + group.length();
        }

        return input;
    }

    private static String formatCurrency(String input) {
        String number = input.split("C")[0];
        number = number.replace(",", "");
        double num = Double.parseDouble(number);

        number = NumberFormat.getCurrencyInstance(locale).format(num);
        number = number.replace("$", "\\$");
        return number;
    }

    private static Locale getLocale(String localeId) {
        return new Locale(localeId.split("_")[0], localeId.split("_")[1]);
    }

}
