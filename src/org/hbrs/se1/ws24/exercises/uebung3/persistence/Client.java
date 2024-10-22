package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;


public class Client {

    //Übung 3 - CR3
    //Pattern: Strategy-Pattern, um die Struktur der Container Klasse nicht zu ändern
    public void ausgabe() throws ContainerException {
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);

        Container container = Container.getInstance();
        container.addMember(m1);
        container.addMember(m2);

        MemberView memberView = new MemberView();
        memberView.dump(container.getCurrentList());
    }
}
