package org.hbrs.se1.ws24.exercises.uebung9;

import java.io.UnsupportedEncodingException;

public class TextDocument extends AbstractCoreDocument{

    private String inhalt;
    private Encoding encoding;


    public TextDocument(String inhalt, Encoding encoding){
        this.inhalt = inhalt;
        this.encoding = encoding;
    }

    public int size(){
        try {
            return inhalt.getBytes(encoding.getCode()).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
