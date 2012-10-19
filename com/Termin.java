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
    private GregorianCalendar datum;
    private String dauer;

    /**
     * Konstruktor
     * 
     * @param _ort Ort
     * @param _date Datum
     * @param _dauer Dauer
     */
    public Termin(Ort _ort, GregorianCalendar _date, String _dauer) {
        ort = _ort;
        datum = _date;
        dauer = _dauer;
    }
    
    /**
     * Kopierkonstruktor
     * 
     * @param _t zu kopierender Termin
     */
    public Termin(Termin _t) {
        ort = new Ort(_t.getOrt());
        datum = _t.getDatum();
        dauer = _t.getDauer();
    }

    /**
     * Aendert den derzeitigen Termin
     * 
     * @param _ort Ort
     * @param _date Datum
     * @param _dauer Dauer
     */
    public void setDatum(Ort _ort, GregorianCalendar _date, String _dauer) {
        ort = _ort;
        datum = _date;
        dauer = _dauer;
    }

    /**
     * Getter fuers Datum
     * 
     * @return 
     */
    public GregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Getter fuer die Dauer
     * 
     * @return 
     */
    public String getDauer() {
        return dauer;
    }
    
    /**
     * Getter fuer den Ort
     * 
     * @return 
     */
    public Ort getOrt() {
        return ort;
    }

    @Override
    /**
     * Liefert die Daten des Termins als String getrennt durch Leerzeichen in
     * der Reihenfolge: Ort, Datum und Dauer
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(datum.getTime()) + " - " + dauer;
    }
}
