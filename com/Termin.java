package com;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Stack;

public abstract class Termin implements Comparable<Termin> {

    private Ort ort;
    private GregorianCalendar datum;
    private String dauer;
    private Stack<Termin> stack;
    private HashSet<Mitglied> teilnehmer;

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer und teilnehmer sollten nicht null sein.
     * ERROR: Das wird hier nicht ueberprueft.
     */
    public Termin(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer) {
        this.ort = ort;
        this.datum = datum;
        this.dauer = dauer;
        this.stack = new Stack<Termin>();
        this.teilnehmer = teilnehmer;
        
        for(Mitglied m : teilnehmer) {
            m.addTermin(this);
        }
    }

    /**
     * Vorbedingung
     * 
     * Uebergebener Termin darf nicht null sein, da sonst eine NullpointerException entsteht.
     * ERROR: Das wird hier nicht ueberprueft.
     */
    public Termin(Termin t) {
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
        this.stack = new Stack<Termin>();
        this.teilnehmer = new HashSet<Mitglied>(t.getTeilnehmer());
    }

    /**
     * Vorbedingung
     * 
     * Uebergebener Termin darf nicht null sein, da sonst eine NullpointerException entsteht.
     * ERROR: Das wird hier nicht ueberprueft.
     * 
     * Nachbedingung
     * 
     * Retournierter Termin hat die Daten des uebergebenen Termins uebernommen.
     */
    protected Termin setTermin(Termin t) {
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
        this.teilnehmer = t.getTeilnehmer();
        return this;
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Teilnehmer hinzugefuegt werden und ihm der Termin uebergeben werden konnte
     * false andernfalls.
     */
    protected boolean teilnehmerHinzufuegen(Mitglied m) {
        boolean ok = teilnehmer.add(m);
        if (ok) {
            m.addTermin(this);
        }
        return ok;
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Teilnehmer entfernt und ihm der Termin entzogen werden konnte,
     * false andernfalls.
     */
    protected boolean teilnehmerEntfernen(Mitglied m) {
        boolean ok = teilnehmer.remove(m);
        if (ok) {
            m.terminLoeschen(this);
        }
        return ok;
    }

    protected GregorianCalendar getDatum() {
        return datum;
    }

    protected String getDauer() {
        return dauer;
    }
    
    protected Ort getOrt() {
        return ort;
    }
    
    public HashSet<Mitglied> getTeilnehmer() {
        return teilnehmer;
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Termin sollte nicht null sein.
     * ERROR: Das wird hier nicht ueberprueft.
     */
    protected void pushToStack(Termin t) {
        stack.push(t);
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Termin
     * der Termin vor der letzten Aenderung,
     * null im Falle eines Misserfolgs.
     */
    protected Termin popFromStack() {
        if(!stack.empty()) {
            return stack.pop();
        }
        return null;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(datum.getTime()) + " - " + dauer;
    }
    
    @Override
    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * -1, falls der Termin vor dem Vergleichstermin stattfindet,
     * 1, falls der Termin nach dem Vergleichstermin stattfindet,
     * 0, falls beide Termine zur gleichen Zeit stattfinden.
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