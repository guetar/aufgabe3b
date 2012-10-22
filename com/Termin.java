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
public abstract class Termin implements Comparable<Termin> {

    private Ort ort;
    private GregorianCalendar datum;
    private String dauer;
    private Stack<Termin> stack;

    /**
     * Konstruktor
     * 
     * @param ort Ort
     * @param date Datum
     * @param dauer Dauer
     */
    public Termin(Ort ort, GregorianCalendar datum, String dauer) {
        this.ort = ort;
        this.datum = datum;
        this.dauer = dauer;
        this.stack = new Stack<Termin>();
    }
    
    /**
     * Kopierkonstruktor
     * 
     * @param t zu kopierender Termin
     */
    public Termin(Termin t) {
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
        this.stack = new Stack<Termin>();
    }

    /**
     * Aendert den derzeitigen Termin
     * 
     * @param ort Ort
     * @param date Datum
     * @param dauer Dauer
     */
    protected Termin setTermin(Termin t) {
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
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
     * @param t 
     */
    protected void pushToStack(Termin t) {
        stack.push(t);
    }
    
    /**
     * Holt das letzte Element vom Stack
     * 
     * @return 
     */
    protected Termin popFromStack() {
        if(!stack.empty()) {
            return stack.pop();
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
    
    @Override
    /**
     * Eigene CompareTo Methode um zwei Termine zu vergleichen
     * 
     * @param t Vergleichstermin
     * @return 
     */
    public int compareTo(Termin t) {
        if(this.getDatum().before(t.getDatum())) {
            return -1;
        } else if(this.getDatum().after(t.getDatum())) {
            return 1;
        }
        return 0;
    }
}