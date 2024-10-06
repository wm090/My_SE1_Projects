package org.hbrs.se1.ws24.exercises.uebung1.control;

import java.util.ArrayList;

public class GermanTranslator implements Translator {

	public String date = "Okt/2024"; // Default-Wert

	static ArrayList<String> list = new ArrayList<>();
	static { //Verwendung des static keyboard mit einem Block, da Felder-Initialisierungen
		     // nur innerhalb eines Blocks möglich sind.
		list.add("eins");
		list.add("zwei");
		list.add("drei");
		list.add("vier");
		list.add("fünf");
		list.add("sechs");
		list.add("sieben");
		list.add("acht");
		list.add("neun");
		list.add("zehn");

	}
	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	@Override
	public String translateNumber(int number) {
		try {
			// Der Index muss um 1 angepasst werden, da die Liste nullbasiert ist
			return list.get(number - 1);
		} catch (IndexOutOfBoundsException e) {
			// Wenn die Zahl außerhalb des Bereichs 1-10 liegt, wird die Exception ausgelöst
			return "Übersetzung der Zahl " + number + " nicht möglich (Version " + Translator.version + ")";
		}
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2024"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}