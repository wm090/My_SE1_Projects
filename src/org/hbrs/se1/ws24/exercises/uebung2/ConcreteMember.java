package org.hbrs.se1.ws24.exercises.uebung2;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {

    private Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    //FA3: To-String Methode
    @Override
    public String toString() {
        return "Member (ID = " + this.id + ")";
    }
}
