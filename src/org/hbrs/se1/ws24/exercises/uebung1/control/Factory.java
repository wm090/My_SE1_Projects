package org.hbrs.se1.ws24.exercises.uebung1.control;

public class Factory {
    public static Translator fabrikTranslator(){
        GermanTranslator gt = new GermanTranslator();
        gt.setDate("Okt/2024");
        return gt;

    }
}