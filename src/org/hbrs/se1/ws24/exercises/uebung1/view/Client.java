package org.hbrs.se1.ws24.exercises.uebung1.view;

import org.hbrs.se1.ws24.exercises.uebung1.control.Factory;
import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;

public class Client {

	public static void main(String[] args) {
		Client client = new Client();
		client.display(10);  // Test mit einer gültigen Zahl
		client.display(12); // Test mit einer ungültigen Zahl
	}
	/**
	 * Methode zur Ausgabe einer Zahl auf der Console
	 * (auch bezeichnet als CLI, Terminal)
	 *
	 */
	void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber
		// mit dem übergegebenen Wert der Variable aNumber
		// aufgerufen werden.
		//
		// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

		Translator t = Factory.fabrikTranslator();

		System.out.println("Das Ergebnis der Berechnung: " +
				"[das Ergebnis an dieser Stelle]" + t.translateNumber(aNumber));

	}
}










