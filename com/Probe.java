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
     * @param _ort Ort
     * @param _datum Datum
     * @param _dauer Dauer
     * @param _miete Miete
     */
    public Probe(Ort _ort, GregorianCalendar _datum, String _dauer, int _miete) {
        super(_ort, _datum, _dauer);
        miete = _miete;
    }

    /**
     * Aendert die derzeitige Probe
     * 
     * @param _ort Ort
     * @param _datum Datum
     * @param _dauer Dauer
     * @param _miete Miete
     */
    /*public Probe(Probe _p) {
        super.setTermin(_p);
        miete = _p.getMiete();
    }*/

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
