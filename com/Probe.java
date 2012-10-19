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

    public Probe(Ort _ort, GregorianCalendar _von, String _dauer, int _miete) {
        super(_ort, _von, _dauer);
        miete = _miete;
    }

    public void setDate(Ort _ort, GregorianCalendar _von, String _dauer, int _miete) {
        super.setDate(_ort, _von, _dauer);
        miete = _miete;
    }

    public int getMiete() {
        return miete;
    }

    @Override
    public String toString() {
        return super.toString() + " " + miete + " Euro Miete";
    }
}
