/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;

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
    public Auftritt(Ort ort, GregorianCalendar datum, String dauer, int gage) {
        super(ort, datum, dauer);
        this.gage = gage;
    }

    /**
     * Aendert den derzeitigen Auftritt und wirft den alten auf den Stack
     * 
     * @param a neuer Auftritt
     */
    public Auftritt Auftritt(Auftritt a) {
        super.pushToStack(this);
        super.setTermin(a);
        gage = a.getGage();
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
