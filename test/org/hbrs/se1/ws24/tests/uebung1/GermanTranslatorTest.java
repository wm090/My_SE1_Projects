package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

    @Test
    void aTest() {
        Translator translator = new GermanTranslator();
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("zwei", translator.translateNumber(2));
        assertEquals("zehn", translator.translateNumber(10));

    }
    @Test
    void bTest() {
        Translator translator = new GermanTranslator();
        assertEquals("Übersetzung der Zahl 0 nicht möglich (Version 1.0)", translator.translateNumber(0));
        assertEquals("Übersetzung der Zahl -9 nicht möglich (Version 1.0)", translator.translateNumber(-9));
    }

}