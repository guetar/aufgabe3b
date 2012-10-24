/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
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
    private ArrayList<Song> versionen;
    
    /**
     * Konstruktor
     * 
     * @param name Name
     * @param laenge Laenge
     * @param von Entstehungsdatum
     */
    public Song(String name, String laenge, GregorianCalendar von) {
        this.name = name;
        this.laenge = laenge;
        this.von = von;
        this.versionen = new ArrayList<Song>();
        this.versionen.add(new Song(this));
    }
    
    /**
     * Konstruktor fuer Versionen
     * 
     * @param name Name
     * @param laenge Laenge
     */
    public Song(String name, String laenge) {
        this.name = name;
        this.laenge = laenge;
    }
    
    /**
     * Kopierkonstruktor
     * 
     * @param s zu kopierender Song
     */
    public Song(Song s) {
        this.name = s.name;
        this.laenge = s.laenge;
    }
    
    /**
     * Fuegt eine neue Version des Songs zu dessen Versionen hinzu.
     * 
     * @param s hinzuzufuegender Song
     * @return Erfolg
     */
    public Boolean versionHinzufuegen(Song s) {
        return versionen.add(s);
    }
    
    /**
     * Gibt eine Liste der Versionen des Songs zurueck.
     * 
     * @return Versionen
     */
    public ArrayList<Song> getVersionen() {
        return versionen;
    }
    
    /**
     * Getter fuers Entstehungsdatum
     * 
     * @return Entstehungsdatum
     */
    public GregorianCalendar getVon() {
        return von;
    }
    
    @Override
    /**
     * Liefert die Daten des Songs als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Laenge
     */
    public String toString() {
        return name + " " + laenge;
    }
}
