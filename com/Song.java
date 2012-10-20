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
     * @param name Name
     * @param laenge Laenge
     * @param von Entstehungsdatum
     */
    public Song(String name, String laenge, GregorianCalendar von)
    {
        this.name = name;
        this.laenge = laenge;
        this.von = von;
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
