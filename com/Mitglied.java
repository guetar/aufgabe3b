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

    public Mitglied(String _name, String _tel, String _instrument, GregorianCalendar _von, GregorianCalendar _bis) {
        name = _name;
        tel = _tel;
        instrument = _instrument;
        von = _bis;
        bis = _bis;
    }

    public GregorianCalendar getVon() {
        return von;
    }

    public GregorianCalendar getBis() {
        return bis;
    }

    @Override
    public String toString() {
        return name + " " + tel + " " + instrument;
    }
}
