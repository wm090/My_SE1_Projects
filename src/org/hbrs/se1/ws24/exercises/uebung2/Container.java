package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    // Liste zur Speicherung von Member-Objekten.
    private List<Member> memberContainer;

    public Container() {
        memberContainer = new ArrayList<>();
    }

    //Main zur Kontrolle
    public static void main(String[] args) {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);
        Member m4 = new ConcreteMember(1);

        Container c = new Container();

        try {
            c.addMember(m1);
            c.addMember(m2);
            c.addMember(m3);
            //c.addMember(m1);
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }


        //System.out.println(c.deleteMember(4));

        c.dump();
        System.out.println(c.size());
    }

    //FA1:
    public void addMember(Member member) throws ContainerException {
        for (Member m : memberContainer) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(member.getID());
            }
        }
        memberContainer.add(member);
    }

    //FA2
    public String deleteMember(Integer id) {
        for (Member member : memberContainer) {
            if (member.getID().equals(id)) {
                memberContainer.remove(member);
                return "";
            }
        }
        return "Ein Objekt mit der " + id + " wurde nicht gefunden";
    }
    /*
    Statement:
    - Die Behandlung der Fehler über den Rückgabewert ist nicht hilfreich,
    wenn verschiedene Fehler unterschiedlicher Typen auftreten.
    Deshalb sollten die Fehler mit Exception behandelt werden, da
    verschiedene Exception-Typen für unterschiedliche Fehlertypen verwendet
    werden können. --> Flexiblität
     */

    //FA3
    public void dump() {
        for (Member member : memberContainer) {
            System.out.println(member.toString());
        }
    }

    //FA4
    public int size() {
        return memberContainer.size();
    }

}
