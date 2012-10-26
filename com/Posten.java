package com;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Stack;

/**
 *
 * @author Matthias
 */
public class Posten implements Comparable<Posten> {

    //Invariante: wert!=0
    private int wert;
    //Invariante: kategorie ist entweder 1=Auftritt, 2=Probe, 3=sonstige Einnahme 
    //oder 4=sonstige Ausgabe;andere Werte sind nicht möglich
    //Schlecht: byte haette als Typ gereicht, int verbraucht unnoetig Speicher
    private int kategorie; 
    private String beschreibung;
    private GregorianCalendar datum;
    private Stack<Posten> stack;

    //Vorbedingung: wert!=0, kategorie ist entweder 1,2,3 oder 4, beschreibung!=null.
    //datum!=null, datum enthaelt Informationen ueber Jahr,Monat,Tag,Stunde und Minute
    //Nachbedingung: beschreibung!=null, datum!=null, stack!=null
    public Posten(int wert, int kategorie, String beschreibung, GregorianCalendar datum) {
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.wert = wert;
        this.datum = datum;
        this.stack = new Stack<Posten>();
    }
    
    //Vorbedingung: t!=null
    //Nachbedingung: beschreibung!=null, datum!=null, stack!=null
    public Posten(Termin t) {
        if(t instanceof Auftritt) {
            
            Auftritt a = (Auftritt) t;
            this.beschreibung = "Auftritt";
            this.wert = a.getGage();
            this.kategorie = 1;
            
        } else if(t instanceof Probe) {
            
            Probe p = (Probe) t;
            this.beschreibung = "Probe";
            this.wert = p.getMiete();
            this.kategorie = 2;
            
        }
        this.datum = t.getDatum();
        this.stack = new Stack<Posten>();
    }
    
    //Vorbedingung: p!=null
    //Nachbedingung: this wird auf stack gelegt, beschreibung, wert, datum und 
    //kategorie werden von p uebernommen
    public Posten setPosten(Posten p) {
        stack.push(this);
        this.beschreibung = p.getBeschr();
        this.wert = p.getWert();
        this.datum = p.getDatum();
        this.kategorie = p.getKategorie();
        return this;
    }
    
    public GregorianCalendar getDatum(){
        return datum;
    }
    
    public int getWert(){
        return wert;
    }
    
    public int getKategorie() {
        return kategorie;
    }
    
    public String getBeschr(){
        return beschreibung;
    }
    
    //Nachbedingung: p wird auf stack gelegt
    protected void pushToStack(Posten p) {
        stack.push(p);
    }
    
    //Nachbedingung: stack verliert oberstes Element(falls vorhanden)
    protected Posten popFromStack() {
        if(!stack.empty()) {
            return stack.pop();
        }
        return null;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return sdf.format(datum.getTime()) + ",\t " + wert + " Euro\t" +  beschreibung;
    }
    
    @Override
    //Vorbedingung: p!=null
    public int compareTo(Posten p) {
        if(this.getDatum().before(p.getDatum())) {
            return -1;
        } else if(this.getDatum().after(p.getDatum())) {
            return 1;
        }
        return 0;
    }
}