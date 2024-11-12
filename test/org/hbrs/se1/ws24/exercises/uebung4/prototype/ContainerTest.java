package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    //Deklaration von Objekten
    private Container c;
    UserStory s1;

    @BeforeEach
    void setUp() {
        //Initialisierungen der Objekte
        c = Container.getInstance();
        s1 = new UserStory(1, "Reisender", "Test mit Mastercard und Visa", 5, 5, 3, 5, "Reisen-Projekt");
        c.setPersistenceStrategy(new PersistenceStrategyStream<>());
    }


    @Test
    void size() throws ContainerException {
        c.setPersistenceStrategy(new PersistenceStrategyStream<UserStory>());
        c.addUserStory(s1);
        assertEquals(1, c.size());
    }

    @Test
    public void testGetUserStory() throws ContainerException {
        UserStory s2 = new UserStory(2, "Story 2", "Akzeptanzkriterium", 10.0, 5.0, 8.0, 3.0, "Projekt A");
        c.addUserStory(s2);

        UserStory retrievedStory = c.getUserStory(2);
        Assertions.assertNotNull(retrievedStory);
        Assertions.assertEquals("Story 2", retrievedStory.getTitel());
    }
}