package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.util.List;

// Übung 3 - CR3
public class MemberView {

    public void dump(List<Member> liste) {
        for (Member member : liste) {
            System.out.println(member.toString());
        }
    }
}
