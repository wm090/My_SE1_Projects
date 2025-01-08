package org.hbrs.se1.ws24.exercises.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends AbstractDocument{

    private List<Document> documentList = new ArrayList<Document>();

    public void addDocument(Document doc){
        this.documentList.add(doc);
    }

    public void removeDocument(Document doc){
        this.documentList.remove(doc);
    }

    public int size(){
        int gesamtByte = 0;
        for(Document doc : documentList){
            gesamtByte = gesamtByte + doc.size();
        }
        return gesamtByte;
    }

}
