/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Matthias
 */
public abstract class Termin {

    private Ort ort;
    private GregorianCalendar von;
    private GregorianCalendar bis;

    public Termin(Ort _ort, GregorianCalendar _von, GregorianCalendar _bis) {
        ort = _ort;
        von = _von;
        bis = _bis;
    }
    
    public Termin(Termin _t) {
        ort = new Ort(_t.getOrt());
    }

    public void setDate(Ort _ort, GregorianCalendar _von, GregorianCalendar _bis) {
        ort = _ort;
        von = _von;
        bis = _bis;
    }

    public GregorianCalendar getVon() {
        return von;
    }

    public GregorianCalendar getBis() {
        return bis;
    }
    
    public Ort getOrt() {
        return ort;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(von.getTime()) + " - " + sdf.format(bis.getTime());
    }
}
