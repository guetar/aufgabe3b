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
public class Band {

    private ArrayList<Mitglied> mitglieder;
    private ArrayList<Song> repertoire;
    private Terminverwaltung terminverwaltung;
    private Bilanz bilanz;

    /**
     * Konstruktor
     */
    public Band() {
        this.mitglieder = new ArrayList<Mitglied>();
        this.repertoire = new ArrayList<Song>();
        this.bilanz = new Bilanz();
        this.terminverwaltung = new Terminverwaltung(mitglieder, bilanz);
    }

    // Mitglieder
    
    /**
     * Fuegt der Band ein Mitglied hinzu.
     * 
     * @param m hinzuzufuegendes Mitglied
     * @return Erfolg
     */
    public Boolean mitglied_hinzufuegen(Mitglied m) {
        return mitglieder.add(m);
    }

    /**
     * Entfernt ein Mitglied aus der Band.
     * 
     * @param m zu entferndenes Mitglied
     * @return Erfolg
     */
    public Boolean mitglied_entfernen(Mitglied m) {
        if (mitglieder.contains(m)) {
            return mitglieder.remove(m);
        }
        return false;
    }

    /**
     * Listet alle Mitglieder der Band.
     * 
     * @return Mitglieder
     */
    public ArrayList<Mitglied> mitglieder_auflisten() {
        return mitglieder;
    }

    /**
     * Listet alle Personen, die innerhalb eines gesuchten Zeitraums Mitglieder der Band waren.
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Mitglieder innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Mitglied> mitglieder_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Mitglied> mitglieder_liste = new ArrayList<Mitglied>();

        for (Mitglied m : mitglieder) {
            if (m.getVon().before(bis) && m.getBis().after(von)) {
                mitglieder_liste.add(m);
            }
        }

        return mitglieder_liste;
    }

    // Repertoire
    
    /**
     * Fuegt dem Repertoire der Band einen Song hinzu.
     * 
     * @param s hinzuzufuegender Song
     * @return Erfolg
     */
    public Boolean song_hinzufuegen(Song s) {
        return repertoire.add(s);
    }

    /**
     * Entfernt einen Song aus dem Repertoire der Band.
     * 
     * @param s zu entfernender Song
     * @return Erfolg
     */
    public Boolean song_entfernen(Song s) {
        if (repertoire.contains(s)) {
            return repertoire.remove(s);
        }
        return false;
    }

    /**
     * Listet das Repertoire der Band
     * 
     * @return Repertoire
     */
    public ArrayList<Song> songs_auflisten() {
        return repertoire;
    }

    /**
     * Listet das Repertoire der Band zu einem gewissen Zeitpunkt.
     * 
     * @param datum gesuchter Zeitpunkt
     * @return Repertoire zu dem gesuchten Zeitpunkt
     */
    public ArrayList<Song> songs_auflisten(GregorianCalendar datum, Boolean versionen) {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();

        for(Mitglied m : mitglieder) {
            if(m.getVon().before(datum) && m.getBis().after(datum)) {
                if(!versionen) {
                    repertoire_liste.addAll(m.getRepertoire());
                } else {
                    for(Song s : m.getRepertoire()) {
                        repertoire_liste.addAll(s.getVersionen());
                    }
                }
            }
        }

        return repertoire_liste;
    }
    
    /**
     * Terminverwaltung
     * 
     * @param t Termin
     * @param von Beginn des gesuchten Zeitraums
     * @param bis Ende des gesuchten Zeitraums
     * @param alt alter Termin
     * @param neu neuer Termin
     * @return 
     */
    public Boolean termin_hinzufuegen(Termin t) {
        return terminverwaltung.termin_hinzufuegen(t);
    }
    
    public ArrayList<? extends Termin> termine_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        return terminverwaltung.termine_auflisten(von, bis);
    }
    
    public ArrayList<Probe> proben_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        return terminverwaltung.proben_auflisten(von, bis);
    }
    
    public ArrayList<Auftritt> auftritte_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        return terminverwaltung.auftritte_auflisten(von, bis);
    }
    
    public Boolean termin_aendern(Termin alt, Termin neu) {
        return terminverwaltung.termin_aendern(alt, neu);
    }
    
    public Boolean termin_loeschen(Termin t) {
        return terminverwaltung.termin_loeschen(t);
    }
    
    public Boolean termin_wiederherstellen(Termin t) {
        return terminverwaltung.termin_wiederherstellen(t);
    }

    /**
     * Fügt einen neuen Bilanzposten hinzu
     *
     * @param p hinzuzufuegender Posten
     * @return Erfolg
     */
    public Boolean posten_hinzufuegen(Posten p) {
        return bilanz.addPosten(p);
    }
    
    /**
     * Summiert die Kosten, die innerhalb eines gesuchten Zeitraumes durch das Mieten der Proberaume entstehen
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Kosten, die innerhalb des gesuchten Zeitraumes entstanden sind
     */
    public int kosten_summieren(GregorianCalendar von, GregorianCalendar bis) {
        int kosten = 0;
        ArrayList<Probe> proben_kosten = terminverwaltung.proben_auflisten(von, bis);

        for (Probe p : proben_kosten) {
            kosten += p.getMiete();
        }

        return kosten;
    }

    /**
     * Summiert den Umsatz, der innerhalb eines gesuchten Zeitraumes durch Gagen bei den Auftritten verdient wurde
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Umsatz, der innerhalb des gesuchten Zeitraumes erwirtschaftet werden konnte
     */
    public int umsatz_summieren(GregorianCalendar von, GregorianCalendar bis) {
        int umsatz = 0;
        ArrayList<Auftritt> auftritte_kosten = terminverwaltung.auftritte_auflisten(von, bis);

        for (Auftritt a : auftritte_kosten) {
            umsatz += a.getGage();
        }

        return umsatz;
    }

    /**
     * Summiert den Gewinn, der innerhalb eines gesuchten Zeitraumes erwirtschaftet werden konnte
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Gewinn, der innerhalb des gesuchten Zeitraumes erwirtschaftet werden konnte
     */
    public int gewinn_summieren(GregorianCalendar von, GregorianCalendar bis) {
        return umsatz_summieren(von, bis) - kosten_summieren(von, bis);
    }

    /**
     * liefert Liste mit Orten, die eine bestimmte Infrastruktur haben.
     *
     * @param plaetze Gesuchte Anzahl an Zuschauerplaetzen(oder 0, wenn egal)
     * 
     * @return die Orte, die die bestimmte Infrastruktur haben. null, wenn kein
     * Ort die Voraussetzungen erfuellt.
     */
    public ArrayList<Ort> finde_ort(int plaetze) {
        ArrayList<Ort> gef_orte = new ArrayList<Ort>();
        
        for (Termin t : terminverwaltung.termine_auflisten()) {
            Ort o = t.getOrt();

            if (o.getPlaetze() >= plaetze) {
                    gef_orte.add(o);
            }
        }

        return gef_orte;
    }
}
