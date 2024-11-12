package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2024, Version 1.1
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (VORHER: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Wie bewerten Sie diese Strategie? Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities)
 * sind in einer Klasse, d.h. Container?
 * ToDo: Wie bewerten Sie diese Entscheidung? Was wäre ein sinnvolle Aufteilung (F2, F6)
 *
 */

public class Container {

    // Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
    private List<UserStory> liste;

    // Statische Klassen-Variable, um die Referenz
    // auf das einzige Container-Objekt abzuspeichern
    // Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
    // Todo: Bewertung Thread-Safeness (F1)
    // Todo: Bewertung Speicherbedarf (F1)
    private static Container instance = Container.getInstance();

    // URL der Datei, in der die Objekte gespeichert werden
    final static String LOCATION = "allStories.ser";

    /**
     * Liefert ein Singleton zurück.
     *
     * @return
     */
    public static synchronized Container getInstance() {
        synchronized (Container.class) {
            if (instance == null) {
                instance = new Container();
            }
        }
        return instance;
    }

    private PersistenceStrategy<UserStory> persistenceStrategy;

    public void setPersistenceStrategy(PersistenceStrategy<UserStory> strategy) {
        persistenceStrategy = strategy;
    }


    /**
     * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
     * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
     */
    private Container() {
        liste = new ArrayList<UserStory>();
    }

    /**
     * Start-Methoden zum Starten des Programms
     * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
     */
    public static void main(String[] args) throws Exception {
        // ToDo: Bewertung Exception-Handling (F3, F7)
        Container con = Container.getInstance();
        con.setPersistenceStrategy(new PersistenceStrategyStream<>());
        con.startEingabe();

    }

    /*
     * Diese Methode realisiert eine Eingabe ueber einen Scanner
     * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
     * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
     */

    /* Eingabe über Terminal:
   > cd src
   > javac org/hbrs/se1/ws24/exercises/uebung4/Prototype/Container.java
   > java org.hbrs.se1.ws24.exercises.uebung4.prototype.Container
    */
    public void startEingabe() {

        // Initialisierung des Eingabe-View
        // ToDo: Funktionsweise des Scanners erklären (F3)

        while (true) {
            Scanner scanner = new Scanner(System.in);
//            System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");
            System.out.println("Geben Sie einen Befehl ein (help für Hilfe):");

            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine().toLowerCase();

                switch (command) {
                    case "enter":
                        enterUserStory();
                        break;
                    case "dump":
                        startAusgabe();
                        break;
                    case "store":
                        try {
                            store();
                        } catch (PersistenceException e) {
                            System.out.println("Fehler beim Speichern: " + e.getMessage());
                        }
                        break;
                    case "load":
                        try {
                            load();
                        } catch (PersistenceException e) {
                            System.out.println("Fehler beim Laden: " + e.getMessage());
                        }
                        break;
                    case "exit":
                        System.out.println("Anwendung beendet.");
                        return;
                    case "help":
                        System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump, enter, store");
                        break;
                    default:
                        System.out.println("Unbekannter Befehl. Geben Sie 'help' für Hilfe ein.");
                        break;
                }
            }
        }
    } // Ende der Schleife

    public void enterUserStory() {
        Scanner scanner = new Scanner(System.in);

        int id;
        while (true) {
            System.out.print("ID der User Story eingeben: ");
            id = scanner.nextInt();
            scanner.nextLine();

            if (id >= 0) {
                break;
            } else {
                System.out.println("Ungueltige ID-Eingabe, Versuche nochmal");
            }
        }

        System.out.print("Titel eingeben: ");
        String titel = scanner.nextLine();

        System.out.print("Akzeptanzkriterium eingeben: ");
        String akzeptanceKriterium = scanner.nextLine();

        System.out.print("Mehrwert: ");
        double mehrwert = scanner.nextDouble();

        System.out.print("Strafe: ");
        double strafe = scanner.nextDouble();

        double aufwand;
        while (true) {
            System.out.print("Aufwand: ");
            aufwand = scanner.nextDouble();

            if (aufwand >= 0) {
                break;
            } else {
                System.out.println("Aufwand darf nicht negativ sein! - Versuche nochmal");
            }
        }

        System.out.print("Risiko: ");
        double risiko;
        while (true) {
            System.out.print("Risiko: ");
            risiko = scanner.nextDouble();
            scanner.nextLine();
            if (risiko >= 0) {
                break;
            } else {
                System.out.println("Risiko darf nicht negativ sein! - Versuche nochmal");
            }
        }

        System.out.print("Bitte Ordnen Sie dierser User Story ein Projekt zu: ");
        String projekt = scanner.nextLine();

        UserStory ustories = new UserStory(id, titel, akzeptanceKriterium, mehrwert, strafe, aufwand, risiko, projekt);
        liste.add(ustories);

        System.out.println("User Story hinzugefügt.");
    }

    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe() {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter!

        // [Sortierung ausgelassen]
        // Todo: Implementierung Sortierung (F4)
        System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Titel", "Priorität", "Akzeptanzkriterium");
        System.out.println("--------------------------------------------------------------------");

        liste.stream()
                .sorted((story1, story2) -> Double.compare(story2.getPrio(), story1.getPrio())) // Sortiert nach Priorität absteigend
                .forEach(story -> System.out.printf(
                        "%-10s %-20s %-20.2f %-10s\n",
                        story.getId(),
                        story.getTitel(),
                        story.getPrio(),
                        story.getAkzeptanzkriterium()
                ));

        // Klassische Ausgabe ueber eine For-Each-Schleife
//		for (UserStory story : liste) {
//			System.out.println(story.toString());
//		}

        //  [Variante mit forEach-Methode / Streams (--> Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        //  (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und z.B. Prio >=3
        //  Todo: Implementierung Filterung mit Lambda (F5)
    }

    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy Set");
        }
        try {
            persistenceStrategy.save(liste);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        System.out.println("User Story gespeichert!");
    }

    /*
     * Methode zum Laden der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte geladen.
     *
     */
    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy Set");
        }
        try {
            persistenceStrategy.load();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        System.out.println("User Story geladen!");
    }

    /**
     * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
     *
     * @param userStory
     * @throws ContainerException
     */
    public void addUserStory(UserStory userStory) throws ContainerException {
        if (contains(userStory) == true) {
            ContainerException ex = new ContainerException("ID bereits vorhanden!");
            throw ex;
        }
        liste.add(userStory);
    }

    /**
     * Prüft, ob eine UserStory bereits vorhanden ist
     *
     * @param userStory
     * @return
     */
    private boolean contains(UserStory userStory) {
        int ID = userStory.getId();
        for (UserStory userStory1 : liste) {
            if (userStory1.getId() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ermittlung der Anzahl von internen UserStory
     * -Objekten
     *
     * @return
     */
    public int size() {
        return liste.size();
    }

    /**
     * Methode zur Rückgabe der aktuellen Liste mit Stories
     * Findet aktuell keine Anwendung bei Hr. P.
     *
     * @return
     */
    public List<UserStory> getCurrentList() {
        return this.liste;
    }

    /**
     * Liefert eine bestimmte UserStory zurück
     *
     * @param id
     * @return
     */
    public UserStory getUserStory(int id) {
        for (UserStory userStory : liste) {
            if (id == userStory.getId()) {
                return userStory;
            }
        }
        return null;
    }
}


