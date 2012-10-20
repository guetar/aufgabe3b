/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Stack;

/**
 *
 * @author Matthias
 */
public abstract class Termin {

    private Ort ort;
    private GregorianCalendar datum;
    private String dauer;
    private Stack<Termin> stack;

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
        stack = new Stack<Termin>();
    }

    /**
     * Aendert den derzeitigen Termin
     * 
     * @param _ort Ort
     * @param _date Datum
     * @param _dauer Dauer
     */
    protected Termin setTermin(Termin _t) {
        stack.push(_t);
        ort = _t.getOrt();
        datum = _t.getDatum();
        dauer = _t.getDauer();
        return this;
    }

    /**
     * Getter fuers Datum
     * 
     * @return 
     */
    protected GregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Getter fuer die Dauer
     * 
     * @return 
     */
    protected String getDauer() {
        return dauer;
    }
    
    /**
     * Getter fuer den Ort
     * 
     * @return 
     */
    protected Ort getOrt() {
        return ort;
    }
    
    /**
     * Wirft das uebergebene Element auf den Stack
     * 
     * @param _t 
     */
    protected void pushToStack(Termin _t) {
        stack.push(_t);
    }
    
    /**
     * Holt das letzte Element vom Stack
     * 
     * @return 
     */
    protected Termin popFromStack() {
        if(!stack.empty()) {
            Termin t = stack.pop();
            setTermin(t);
            return this;
        }
        return null;
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
