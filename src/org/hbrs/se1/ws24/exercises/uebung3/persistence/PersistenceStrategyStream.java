package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "userStories.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 15th 2024)
     */
    public void save(List<E> memberList) throws PersistenceException {
        System.out.println("Versuche, Daten zu speichern..."); // Debug-Ausgabe

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(location))) {
            oos.writeObject(memberList); // Liste in Datei schreiben
            System.out.println("Speichern erfolgreich!"); // Debug-Ausgabe
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage()); // Ausgabe des Fehlers
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Fehler beim Speichern aufgetreten: " + e.getMessage());
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        System.out.println("Versuche, Daten zu laden..."); // Debug-Ausgabe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(location))) {
            List<E> loadedList = (List<E>) ois.readObject(); // Liste aus Datei laden
            System.out.println("Laden erfolgreich!"); // Debug-Ausgabe
            return loadedList;
        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage()); // Ausgabe des Fehlers
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Fehler beim Laden aufgetreten: " + e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.err.println("Fehler beim Laden: " + e.getMessage()); // Ausgabe des Fehlers
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    "Fehler beim Laden aufgetreten: " + e.getMessage());
        }
    }
        // Some Coding hints ;-)


        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams

    }

