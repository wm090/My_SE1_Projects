package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

   /*
    //Note: Beim Ausführen von Test, sind Methoden einzelnen zu testen.
      nicht "Current File"!
    */

    //Deklaration von Objekten
    private Container c;
    private Member m1;
    private Member m2;

    @BeforeEach
    void setUp() {
        //Initialisierungen der Objekte
        c = Container.getInstance();
        m1 = new ConcreteMember(1);
        m2 = new ConcreteMember(2);
        c.setPersistenceStrategy(new PersistenceStrategyStream<>());
    }

    //Übung 3 - CR2
    @Test
    void testStoreAndLoad() throws ContainerException, PersistenceException {
        // Container mit einigen Member-Objekten füllen
        c.addMember(m1);
        c.addMember(m2);

        // Speichern der Member-Objekte
        System.out.println("Starte den Speichertest...");
        c.store();

        // Überprüfen, ob die Datei existiert und Daten enthält
        File file = new File("objects.ser");
        assertTrue(file.exists(), "Die Datei wurde nicht erstellt!");
        assertTrue(file.length() > 0, "Die Datei ist leer!");

        // Liste leeren und sicherstellen, dass der Container leer ist
        c.load();
        assertEquals(2, c.size());
    }

    //Übung 3 - CR4
    @Test
    void testAufNULL() throws PersistenceException{
        c.setPersistenceStrategy(null);
        assertThrows(PersistenceException.class, () -> c.store());
    }

    @Test
    void perStrategyMongoDB(){
        c.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        PersistenceException exception = assertThrows(PersistenceException.class, () -> c.store());
        assertTrue(exception.getMessage().contains("MongoDB"));
    }

    @Test
    void wrongLocation() throws PersistenceException{
        // Test für eine fehlerhafte Location des Files bei PersistenceStrategyStream
        PersistenceStrategyStream<Member> strategyStream = new PersistenceStrategyStream<>();
        strategyStream.setLocation("/invalid/directory/");
        c.setPersistenceStrategy(strategyStream);

        assertThrows(PersistenceException.class, () -> c.store());
    }

    @Test
    void roundTripTest() throws ContainerException, PersistenceException{
        c.addMember(m1);
        c.addMember(m2);
        assertEquals(2, c.size());

        c.setPersistenceStrategy(new PersistenceStrategyStream<>());
        c.store();

        c.deleteMember(1);
        c.deleteMember(2);
        c.getCurrentList();
        assertEquals(0, c.size());

        c.load();
        assertEquals(2, c.size());

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


        //Anzeigen Inhalt des Containers
        c.dump();
        //Manuelle Überprüfung, ob der in Ausgabe das gewünschte
        //ausgegeben wird.
        assertEquals(0, c.size());
    }

    @Test
    void size() throws ContainerException {

        //Festellung der Größe nach Hinzufügen und Löschen von Objekten
        c.addMember(m1);
        c.deleteMember(1);
        assertEquals(0, c.size());
        c.deleteMember(1);
        c.deleteMember(2);
        c.addMember(m2);
        assertThrows(ContainerException.class, () -> c.addMember(m2));
        c.deleteMember(2);
        assertEquals(0, c.size());

    }
}