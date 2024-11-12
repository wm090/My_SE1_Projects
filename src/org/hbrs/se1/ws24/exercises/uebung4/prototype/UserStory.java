package org.hbrs.se1.ws24.exercises.uebung4.prototype;

import java.io.Serializable;

public class UserStory implements Serializable {
    // ToDo: Sind die Attribute der Klasse UserStory vollständig? Wie sieht es mit den
    //  Sichtbarkeiten aus? (F4)

    private String titel;
    private int id = 0;
    private String akzeptanzkriterium;
    private double mehrwert;
    private double strafe;
    private double aufwand;
    private double risiko;
    private double prio = 0.0;
    private String project;

    public UserStory(int id, String titel, String akzeptanzkriterium, double mehrwert, double strafe, double aufwand, double risiko, String project) {
        this.id = id;
        this.titel = titel;
        this.akzeptanzkriterium = akzeptanzkriterium;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risiko = risiko;
        this.project = project;
        this.prioBerechnung();
    }

    public void prioBerechnung() {
        this.prio = (mehrwert + strafe) / (aufwand + risiko);
    }

    public UserStory() {
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAkzeptanzkriterium() {
        return akzeptanzkriterium;
    }

    public void setAkzeptanzkriterium(String akzeptanzkriterium) {
        this.akzeptanzkriterium = akzeptanzkriterium;
    }

    public double getMehrwert() {
        return mehrwert;
    }

    public void setMehrwert(double mehrwert) {
        this.mehrwert = mehrwert;
    }

    public double getStrafe() {
        return strafe;
    }

    public void setStrafe(double strafe) {
        this.strafe = strafe;
    }

    public double getRisiko() {
        return risiko;
    }

    public void setRisiko(double risiko) {
        this.risiko = risiko;
    }

    public double getAufwand() {
        return aufwand;
    }

    public void setAufwand(double aufwand) {
        this.aufwand = aufwand;
    }

    public double getPrio() {
        return prio;
    }

    public void setPrio(double prio) {
        this.prio = prio;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "titel='" + titel + '\'' +
                ", id=" + id +
                ", akzeptanzkriterium='" + akzeptanzkriterium + '\'' +
                ", mehrwert=" + mehrwert +
                ", strafe=" + strafe +
                ", aufwand=" + aufwand +
                ", risiko=" + risiko +
                ", prio=" + prio +
                ", project='" + project + '\'' +
                '}';
    }
}




