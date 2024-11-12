package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.MemberView;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {

    //Deklaration eines privaten und statischen ContainerVariable
    private static Container instance;

    // Liste zur Speicherung von Member-Objekten.
    private List<Member> memberContainer;

    //Konstruktor Zugriffsrecht auf private gestellt
    private Container() {
        memberContainer = new ArrayList<>();
    }

    //Übung 3-----------------------------------------------------------------------------
    //CR1 - Singleton Design Pattern
    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    //CR2
    private PersistenceStrategy<Member> persistenceStrategy;

    public void setPersistenceStrategy(PersistenceStrategy<Member> strategy) {
        persistenceStrategy = strategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy Set");
        }
        persistenceStrategy.save(memberContainer);
    }


    public void load() throws PersistenceException {
        if (persistenceStrategy == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy Set");
        }
        List<Member> members = persistenceStrategy.load();
        memberContainer = members != null ? members : new ArrayList<>();
    }

    //CR3
    public List<Member> getCurrentList() {
        return new ArrayList<>(memberContainer);
    }

    //Übung 2-Ende-------------------------------------------------------------------------

    //Main zur Kontrolle
    public static void main(String[] args) {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);
        Member m4 = new ConcreteMember(1);

       Container c = new Container(); //Funktioniert nur, weil innerhalb der Klasse.
       Container c2 = new Container(); //Enthält nicht das gleiche wie c.

        //Singleton Design Pattern
        Container c3 = Container.getInstance();
        Container c4 = Container.getInstance(); //Enthält genau das gleiche wie c3, da nur ein Objekt

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
        System.out.println("C2");
        c2.dump();
        System.out.println("C2");

    }

    //Übung 2-------------------------------------------------------------------------------------
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
    //Übung 2-Ende-------------------------------------------------------------------------------

}
