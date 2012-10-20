/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;

/**
 *
 * @author Matthias
 */
public class Probe extends Termin {

    private int miete;

    /**
     * Konstruktor
     * 
     * @param ort Ort
     * @param datum Datum
     * @param dauer Dauer
     * @param miete Miete
     */
    public Probe(Ort ort, GregorianCalendar datum, String dauer, int miete) {
        super(ort, datum, dauer);
        this.miete = miete;
    }

    /**
     * Aendert die derzeitige Probe und wirft die alte auf den Stack
     * 
     * @param p neue Probe
     */
    public Probe setTermin(Probe p) {
        super.setTermin(p);
        miete = p.getMiete();
        return this;
    }

    /**
     * Getter für Miete
     * 
     * @return Miete
     */
    public int getMiete() {
        return miete;
    }

    @Override
    /**
     * Liefert die Daten der Probe als String getrennt durch Leerzeichen in
     * der Reihenfolge: Ort, Datum, Dauer und Miete
     */
    public String toString() {
        return super.toString() + " " + miete + " Euro Miete";
    }
}
