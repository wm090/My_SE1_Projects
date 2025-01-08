package org.hbrs.se1.ws24.exercises.uebung9;

public enum Encoding {

    UTF8("UTF-8"),
    UTF16("UTF-16"),
    UTF32("UTF-32");

    private final String code;
    private Encoding(String code) {
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
