/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;

/**
 *
 * @author guetar
 */
public class Mitglied {

    private String name;
    private String tel;
    private String instrument;
    private GregorianCalendar von;
    private GregorianCalendar bis;

    /**
     * Konstruktor
     * 
     * @param _name Name
     * @param _tel Telefonnummer
     * @param _instrument Instrument
     * @param _von Eintrittsdatum in die Band
     * @param _bis Austrittsdatum aus der Band
     */
    public Mitglied(String _name, String _tel, String _instrument, GregorianCalendar _von, GregorianCalendar _bis) {
        name = _name;
        tel = _tel;
        instrument = _instrument;
        von = _bis;
        bis = _bis;
    }

    /**
     * Eintrittsdatum in die Band
     * 
     * @return Eintrittsdatum in die Band
     */
    public GregorianCalendar getVon() {
        return von;
    }

    /**
     * Austrittsdatum aus der Band
     * 
     * @return Austrittsdatum aus der Band
     */
    public GregorianCalendar getBis() {
        return bis;
    }

    @Override
    /**
     * Liefert die Daten des Mitglieds als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Telefonnummer und Instrument
     */
    public String toString() {
        return name + " " + tel + " " + instrument;
    }
}
