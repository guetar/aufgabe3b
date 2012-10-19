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
public class Auftritt extends Termin {

    private int gage;

    public Auftritt(Ort _ort, GregorianCalendar _von, String _dauer, int _gage) {
        super(_ort, _von, _dauer);
        gage = _gage;
    }

    public int getGage() {
        return gage;
    }

    @Override
    public String toString() {
        return super.toString() + " " + gage + " Euro Gage";
    }
}
