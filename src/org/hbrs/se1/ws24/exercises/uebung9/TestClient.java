package org.hbrs.se1.ws24.exercises.uebung9;

public class TestClient {

    public static void main(String[] args) {
        TestClient t = new TestClient();
        t.run();
    }
    public void run() {
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setID(1);

        Document doc2 = new TextDocument("Die SE1 Klausur", Encoding.UTF8);
        doc2.setID(3);

        ComplexDocument doc3 = new ComplexDocument();
        doc3.setID(1);

        Document doc4 = new GraficDocument("Loclahost: 8080");
        doc4.setID(2);

        Document doc5 = new TextDocument("SE 1", Encoding.UTF8);
        doc5.setID(3);
        doc3.addDocument(doc4);
        doc3.addDocument(doc5);

        doc0.addDocument(doc3);
        doc0.addDocument(doc2);

        System.out.println("Size " + doc0.size() + "Bytes");



    }
}
