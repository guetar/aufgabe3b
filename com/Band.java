/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author guetar
 */
public class Band {

    private Mitgliedsverwaltung mitgliedsVerwaltung;
    private ArrayList<Song> repertoire;
    private Kalender kalender;
    private Bilanz bilanz;

    /**
     * Konstruktor
     */
    public Band() {
        this.mitgliedsVerwaltung = new Mitgliedsverwaltung();
        this.repertoire = new ArrayList<Song>();
        this.kalender = new Kalender();
        this.bilanz = new Bilanz();
    }

    // Mitglieder
    /**
     * Fuegt der Band ein Mitglied hinzu.
     *
     * @param m hinzuzufuegendes Mitglied
     * @return Erfolg
     */
    public Boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar eintrittsDatum) {
        return mitgliedsVerwaltung.mitgliedHinzufuegen(m, eintrittsDatum);
    }

    /**
     * Entfernt ein Mitglied aus der Band.
     *
     * @param m zu entferndenes Mitglied
     * @return Erfolg
     */
    public Boolean mitgliedEntfernen(Mitglied m, GregorianCalendar austrittsdatum) {
        return mitgliedsVerwaltung.mitgliedEntfernen(m, austrittsdatum);
    }

    /**
     * Listet alle Mitglieder der Band.
     *
     * @return Mitglieder
     */
    public HashSet<Mitglied> mitgliederAuflisten() {
        return mitgliedsVerwaltung.mitgliederAuflisten();
    }

    /**
     * Listet alle Personen, die innerhalb eines gesuchten Zeitraums Mitglieder
     * der Band waren.
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Mitglieder innerhalb des gesuchten Zeitraumes
     */
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        return mitgliedsVerwaltung.mitgliederAuflisten(date);
    }

    // Repertoire
    /**
     * Fuegt dem Repertoire der Band einen Song hinzu.
     *
     * @param s hinzuzufuegender Song
     * @return Erfolg
     */
    public Boolean songHinzufuegen(Song s) {
        return repertoire.add(s);
    }

    /**
     * Entfernt einen Song aus dem Repertoire der Band.
     *
     * @param s zu entfernender Song
     * @return Erfolg
     */
    public Boolean songEntfernen(Song s) {
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
    public ArrayList<Song> songsAuflisten() {
        return repertoire;
    }

    /**
     * Listet das Repertoire der Band zu einem gewissen Zeitpunkt.
     *
     * @param datum gesuchter Zeitpunkt
     * @return Repertoire zu dem gesuchten Zeitpunkt
     */
    public ArrayList<Song> songsAuflisten(GregorianCalendar datum, Boolean versionen) {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();
        HashSet<Mitglied> mitglieder = mitgliedsVerwaltung.mitgliederAuflisten(datum);

        for (Mitglied m : mitglieder) {
            if (!versionen) {
                repertoire_liste.addAll(m.getRepertoire());
            } else {
                for (Song s : m.getRepertoire()) {
                    repertoire_liste.addAll(s.getVersionen());
                }
            }
        }

        return repertoire_liste;
    }

    /**
     * Kalender und Bilanz
     *
     * @param t Termin
     * @param von Beginn des gesuchten Zeitraums
     * @param bis Ende des gesuchten Zeitraums
     * @param alt alter Termin
     * @param neu neuer Termin
     * @return
     */
    public Boolean terminHinzufuegen(Termin t) {
        return kalender.terminHinzufuegen(t) && bilanz.postenHinzufuegen(new Posten(t));
    }

    public ArrayList<? extends Termin> termineAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.termineAuflisten(von, bis);
    }

    public ArrayList<Probe> probenAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.probenAuflisten(von, bis);
    }

    public ArrayList<Auftritt> auftritteAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.auftritteAuflisten(von, bis);
    }

    public Abstimmung abstimmenTermin(Termin _t) {
        return new Abstimmung(mitgliedsVerwaltung.mitgliederAuflisten(), _t);
    }

    public Boolean terminAendern(Termin alt, Termin neu) {
        HashSet<Mitglied> mitglieder = mitgliedsVerwaltung.mitgliederAuflisten();

        Termin t = kalender.terminAendern(alt, neu);

        if (t != null) {
            bilanz.postenAendern(new Posten(alt), new Posten(neu));
            for (Mitglied m : mitglieder) {
                m.message("Folgender Termin wurde geändert: " + t.toString());
            }
            return true;
        }
        return false;
    }

    public Boolean terminLoeschen(Termin t) {
        HashSet<Mitglied> mitglieder = mitgliedsVerwaltung.mitgliederAuflisten();

        if (kalender.terminLoeschen(t)) {
            bilanz.postenLoeschen(new Posten(t));
            for (Mitglied m : mitglieder) {
                m.message("Folgender Termin wurde abgesagt: " + t.toString());
            }
            return true;
        }
        return false;
    }

    public Boolean terminWiederherstellen(Termin t) {
        return (kalender.terminWiederherstellen(t) != null) ? true : false;
    }

    /**
     * Fügt einen neuen Bilanzposten hinzu
     *
     * @param p hinzuzufuegender Posten
     * @return Erfolg
     */
    public Boolean postenHinzufuegen(Posten p) {
        return bilanz.postenHinzufuegen(p);
    }

    /**
     * Getter für die Bilanz
     *
     * @return Bilanz
     */
    public Bilanz getBilanz() {
        return bilanz;
    }

    /**
     * Summiert die Kosten, die innerhalb eines gesuchten Zeitraumes durch das
     * Mieten der Proberaume entstehen
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Kosten, die innerhalb des gesuchten Zeitraumes entstanden sind
     */
    public int kostenSummieren(GregorianCalendar von, GregorianCalendar bis) {
        int kosten = 0;
        ArrayList<Probe> probenKosten = kalender.probenAuflisten(von, bis);

        for (Probe p : probenKosten) {
            kosten += p.getMiete();
        }

        return kosten;
    }

    /**
     * Summiert den Umsatz, der innerhalb eines gesuchten Zeitraumes durch Gagen
     * bei den Auftritten verdient wurde
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Umsatz, der innerhalb des gesuchten Zeitraumes erwirtschaftet
     * werden konnte
     */
    public int umsatzSummieren(GregorianCalendar von, GregorianCalendar bis) {
        int umsatz = 0;
        ArrayList<Auftritt> auftritteKosten = kalender.auftritteAuflisten(von, bis);

        for (Auftritt a : auftritteKosten) {
            umsatz += a.getGage();
        }

        return umsatz;
    }

    /**
     * Summiert den Gewinn, der innerhalb eines gesuchten Zeitraumes
     * erwirtschaftet werden konnte
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Gewinn, der innerhalb des gesuchten Zeitraumes erwirtschaftet
     * werden konnte
     */
    public int gewinnSummieren(GregorianCalendar von, GregorianCalendar bis) {
        return umsatzSummieren(von, bis) - kostenSummieren(von, bis);
    }

    /**
     * liefert Liste mit Orten, die eine bestimmte Infrastruktur haben.
     *
     * @param plaetze Gesuchte Anzahl an Zuschauerplaetzen(oder 0, wenn egal)
     *
     * @return die Orte, die die bestimmte Infrastruktur haben. null, wenn kein
     * Ort die Voraussetzungen erfuellt.
     */
    public ArrayList<Ort> findeOrt(int plaetze) {
        ArrayList<Ort> gefOrte = new ArrayList<Ort>();

        for (Termin t : kalender.termineAuflisten()) {
            Ort o = t.getOrt();

            if (o.getPlaetze() >= plaetze) {
                gefOrte.add(o);
            }
        }

        return gefOrte;
    }
}