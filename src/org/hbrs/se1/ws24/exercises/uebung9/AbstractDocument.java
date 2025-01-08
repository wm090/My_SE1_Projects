package org.hbrs.se1.ws24.exercises.uebung9;

public class AbstractDocument implements Document {

    private int id;

    public int size() {
        return 1200;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }
}
