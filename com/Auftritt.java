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
     * @param _ort Ort
     * @param _datum Datum
     * @param _dauer Dauer
     * @param _gage Gage
     */
    public Auftritt(Ort _ort, GregorianCalendar _datum, String _dauer, int _gage) {
        super(_ort, _datum, _dauer);
        gage = _gage;
    }

    /**
     * Aendert den derzeitigen Auftritt und wirft den alten auf den Stack
     * 
     * @param _a neuer Auftritt
     */
    public Auftritt Auftritt(Auftritt _a) {
        super.pushToStack(this);
        super.setTermin(_a);
        gage = _a.getGage();
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
