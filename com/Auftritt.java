package com;

import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Auftritt extends Termin {

    private int gage;

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer, teilnehmer und gage sollten nicht null sein.
     * ERROR: Das wird hier nicht ueberprueft.
     */
    public Auftritt(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int gage) {
        super(ort, datum, dauer, teilnehmer);
        this.gage = gage;
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Auftritt sollte nicht null sein.
     * ERROR: Das wird hier nicht ueberprueft.
     */
    public Auftritt(Auftritt a) {
        super(a);
        this.gage = a.getGage();
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Auftritt sollte nicht null sein.
     * ERROR: Das wird hier nicht ueberprueft.
     * 
     * Nachbedingung
     * 
     * Retournierter Auftritt hat die Daten des uebergebenen Auftritts uebernommen.
     */
    public Auftritt setAuftritt(Auftritt a) {
        super.pushToStack(new Auftritt(this));
        super.setTermin(a);
        this.gage = a.getGage();
        return this;
    }
    
    public int getGage() {
        return gage;
    }

    @Override
    public String toString() {
        return super.toString() + " " + gage + " Euro Gage";
    }
}
