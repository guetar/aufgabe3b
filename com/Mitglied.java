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
public class Mitglied {

    private String name;
    private String tel;
    private String instrument;
    private ArrayList<Song> repertoire;
    private ArrayList<String> messages;
    private ArrayList<Termin> termine;

    /**
     * Konstruktor
     * 
     * @param name Name
     * @param tel Telefonnummer
     * @param instrument Instrument
     * @param von Eintrittsdatum in die Band
     * @param bis Austrittsdatum aus der Band
     */
    public Mitglied(String name, String tel, String instrument) {
        this.name = name;
        this.tel = tel;
        this.instrument = instrument;
        this.repertoire = new ArrayList<Song>();
        this.messages = new ArrayList<String>();
        this.termine = new ArrayList<Termin>();
    }
    
    /**
     * Speichert die Nachrichten zu Terminaenderungen, die das Mitglied betreffen
     * 
     * @param m Nachricht
     * @return Erfolg
     */
    public Boolean message(String m) {
        return messages.add(m);
    }
    
    /**
     * Liefert alle eingegangenen Nachrichten
     * 
     * @return Nachrichten 
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    /**
     * Fuegt dem Repertoire des Mitglieds einen Song hinzu.
     * 
     * @param s hinzuzufuegender Song
     * @return Erfolg
     */
    public Boolean songHinzufuegen(Song s) {
        return repertoire.add(s);
    }
    
    /**
     * Gibt das Repertoire des Mitglieds zurueck
     */
    public ArrayList<Song> getRepertoire(GregorianCalendar datum) {
        ArrayList<Song> repertoireListe = new ArrayList<Song>();
        
        for (Song s : repertoire) {
            if (s.getVon().before(datum)) {
                repertoireListe.add(s);
            }
        }
        return repertoireListe;
    }
    
    /**
     * Fuegt Termin hinzu
     * 
     * @param t hinzuzufuegender Song
     * @return "true" wenn erfolgreich, "false", wenn schon vorhanden
     */
    public boolean addTermin(Termin t) {
        return termine.add(t);
    }
    
    /**
     * Entfernt Termin
     * 
     * @param t zu entfernender Termin
     * @return "true" wenn erfolgreich, "false", wenn nicht vorhanden
     */
    public boolean terminLoeschen(Termin t) {
        for (Termin tList : termine) {
            if (tList.getDatum().equals(t.getDatum()) && tList.getOrt().getName().equals(t.getOrt().getName())) {
                termine.remove(tList);
                return true;
            }
        }
        return false;
    }

    /**
     * Getter fuer Name
     * 
     * @return Name
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * Liefert die Daten des Mitglieds als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Telefonnummer und Instrument
     */
    public String toString() {
        return name + " " + tel + " " + instrument;
    }

    /**
     * getter fuer Termine 
     * 
     * @return 
     */
    public ArrayList<Termin> getTermine() {
        return termine;
    }

    /**
     * Ersetzt einen alten Termin durch einen neuen
     * 
     * @param alt alter Termin
     * @param neu neuer Termin
     * @return "true" wenn alt vorhanden war, "false" wenn nicht
     */
    public boolean terminAendern(Termin alt, Termin neu) {
        boolean ok = termine.remove(alt);
        if (ok) {
            termine.add(neu);
        }
        return ok;
    }
}
