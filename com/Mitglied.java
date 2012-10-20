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
     * @param name Name
     * @param tel Telefonnummer
     * @param instrument Instrument
     * @param von Eintrittsdatum in die Band
     * @param bis Austrittsdatum aus der Band
     */
    public Mitglied(String name, String tel, String instrument, GregorianCalendar von, GregorianCalendar bis) {
        this.name = name;
        this.tel = tel;
        this.instrument = instrument;
        this.von = bis;
        this.bis = bis;
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
