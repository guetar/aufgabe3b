/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;

/**
 *
 * @author guetar
 */
public class Song
{
    private String name;
    private String laenge;
    private GregorianCalendar von;
    
    /**
     * Konstruktor
     * 
     * @param _name Name
     * @param _laenge Laenge
     * @param _von Entstehungsdatum
     */
    public Song(String _name, String _laenge, GregorianCalendar _von)
    {
        name = _name;
        laenge = _laenge;
        von = _von;
    }
    
    /**
     * Getter fuers Entstehungsdatum
     * 
     * @return Entstehungsdatum
     */
    public GregorianCalendar getVon()
    {
        return von;
    }
    
    @Override
    /**
     * Liefert die Daten des Songs als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Laenge
     */
    public String toString()
    {
        return name + " " + laenge;
    }
}
