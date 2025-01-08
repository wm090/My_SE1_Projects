package org.hbrs.se1.ws24.exercises.uebung9;

public class GraficDocument extends AbstractCoreDocument{

    private String url;
    private int size = 1200;

    public GraficDocument(String url) {
        this.url = url;
    }

    public int size(){
        return size;
    }
}
