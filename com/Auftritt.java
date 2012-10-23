/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Auftritt extends Termin {

    private int gage;

    /**
     * Konstruktor
     * 
     * @param ort Ort
     * @param datum Datum
     * @param dauer Dauer
     * @param gage Gage
     */
    public Auftritt(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int gage) {
        super(ort, datum, dauer, teilnehmer);
        this.gage = gage;
    }

    /**
     * Aendert den derzeitigen Auftritt und wirft den alten auf den Stack
     * 
     * @param a neuer Auftritt
     */
    public Auftritt(Auftritt a) {
        super(a);
        this.gage = a.getGage();
    }
    
    /**
     * Aendert die Daten des aktuellen Auftritts
     * 
     * @param a neue Daten
     * @return 
     */
    public Auftritt setAuftritt(Auftritt a) {
        super.pushToStack(new Auftritt(this));
        super.setTermin(a);
        this.gage = a.getGage();
        return this;
    }

    /**
     * Getter für Gage
     * 
     * @return Gage
     */
    public int getGage() {
        return gage;
    }

    @Override
    /**
     * Liefert die Daten des Auftritts als String getrennt durch Leerzeichen in
     * der Reihenfolge: Ort, Datum, Dauer und Gage
     */
    public String toString() {
        return super.toString() + " " + gage + " Euro Gage";
    }
}
