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
public class Posten implements Comparable<Posten> {

    private int wert;
    private String beschreibung;
    private GregorianCalendar datum;
    private Stack<Posten> stack;

    /**
     * Erstellt eine neue Instanz von Posten
     *
     * @param wert positiv oder negativ, je nach dem, ob Umsatz oder Kosten
     * @param beschreibung textuelle beschreibung des Postens(null bei einem
     * Termin)
     * @param datum Datum
     * @param termin Termin, auf den sich der Posten bezieht, sonst null
     */
    public Posten(int wert, String beschreibung, GregorianCalendar datum) {
        this.beschreibung = beschreibung;
        this.wert = wert;
        this.datum = datum;
        this.stack = new Stack<Posten>();
    }
    
    /**
     * Erstellt einen Posten aus einem Termin
     * 
     * @param t Termin
     */
    public Posten(Termin t) {
        if(t instanceof Auftritt) {
            
            Auftritt a = (Auftritt) t;
            this.beschreibung = "Auftritt";
            this.wert = a.getGage();
            
        } else if(t instanceof Probe) {
            
            Probe p = (Probe) t;
            this.beschreibung = "Probe";
            this.wert = p.getMiete();
            
        }
        this.datum = t.getDatum();
        this.stack = new Stack<Posten>();
    }
    
    /**
     * Aendert den derzeitigen Posten und wirft den alten auf den Stack
     * 
     * @param p neuer Posten
     * @return geqenderter Posten
     */
    public Posten setPosten(Posten p) {
        stack.push(this);
        this.beschreibung = p.getBeschr();
        this.wert = p.getWert();
        this.datum = p.getDatum();
        return this;
    }
    
    /**
     * Getter Methode des Datums
     * @return Datum des Postens
     */
    public GregorianCalendar getDatum(){
        return datum;
    }
    
    /**
     * Getter Methode des Wert
     * @return Wert des Postens
     */
    public int getWert(){
        return wert;
    }
    
    /**
     * Getter Methode für die Beschreibung
     * @return Beschreibung des Postens
     */
    public String getBeschr(){
        return beschreibung;
    }
    
    /**
     * Wirft das uebergebene Element auf den Stack
     * 
     * @param p 
     */
    protected void pushToStack(Posten p) {
        stack.push(p);
    }
    
    /**
     * Holt das letzte Element vom Stack
     * 
     * @return 
     */
    protected Posten popFromStack() {
        if(!stack.empty()) {
            return stack.pop();
        }
        return null;
    }
    
    @Override
    /**
     * Liefert die Daten des Postens als String getrennt durch Leerzeichen in
     * der Reihenfolge: Ort, Datum und Dauer
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return beschreibung + " " + sdf.format(datum.getTime()) + " " + wert + " Euro";
    }
    
    @Override
    /**
     * Eigene CompareTo Methode um zwei Posten zu vergleichen
     * 
     * @param t Vergleichsposten
     * @return 
     */
    public int compareTo(Posten p) {
        if(this.getDatum().before(p.getDatum())) {
            return -1;
        } else if(this.getDatum().after(p.getDatum())) {
            return 1;
        }
        return 0;
    }
}