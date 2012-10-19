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
    private GregorianCalendar date;
    private String dauer;


    public Termin(Ort _ort, GregorianCalendar _date, String _dauer) {
        ort = _ort;
        date = _date;
        dauer = _dauer;
    }
    
    public Termin(Termin _t) {
        ort = new Ort(_t.getOrt());
    }

    public void setDate(Ort _ort, GregorianCalendar _date, String _dauer) {
        ort = _ort;
        date = _date;
        dauer = _dauer;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getDauer() {
        return dauer;
    }
    
    public Ort getOrt() {
        return ort;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(date.getTime()) + " - " + dauer;
    }
}
