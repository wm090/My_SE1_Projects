package org.hbrs.se1.ws24.exercises.uebung2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    //Deklaration von Objekten
    private Container c;
    private Member m1;
    private Member m2;

    @BeforeEach
    void setUp() {
        //Initialisierungen der Objekte
        c = new Container();
        m1 = new ConcreteMember(1);
        m2 = new ConcreteMember(2);
    }

    @Test
    void addMember() throws ContainerException {
        c.addMember(m1);
        c.addMember(m2);

        assertEquals(2, c.size());
        //Lambda-Ausdruck wurde am Anfang der Prog 2 LV vorgestellt
        assertThrows(ContainerException.class, () -> c.addMember(m1));
        assertEquals(2,c.size());
    }


    @Test
    void deleteMember() throws ContainerException {
        //Hinzufügen von Member-Objekten in den Container
        c.deleteMember(1);
        c.deleteMember(2);
        assertEquals(0, c.size());

        //Hinzufügen eines bereits im Container vorhandenen Objekts
        c.addMember(m1);
        assertEquals(1, c.size());

        //Löschen eines Objekts
        c.deleteMember(1);
        //Löschen eines Objekts, das nicht im Container ist
        c.deleteMember(1);
        assertEquals(0, c.size());

        //Hier wird ein Nachteil ersichtlich, da nichts geworfen wird.
        //assertThrows(ContainerException.class, () -> c.deleteMember(1));
    }

    @Test
    void dump() throws ContainerException {

        c.addMember(m1);
        //Anzeigen Inhalt des Containers
        c.dump();
        //Manuelle Überprüfung, ob der in Ausgabe das gewünschte
        //ausgegeben wird.
        assertEquals(1, c.size());
    }

    @Test
    void size() throws ContainerException {
        //Festellung der Größe nach Hinzufügen und Löschen von Objekten
        c.addMember(m1);
        c.deleteMember(1);
        assertEquals(0, c.size());
        c.deleteMember(1);
        c.addMember(m2);
        assertThrows(ContainerException.class, () -> c.addMember(m2));
        c.deleteMember(2);
        assertEquals(0, c.size());

    }
}