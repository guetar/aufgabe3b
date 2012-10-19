/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 *
 * @author guetar
 */
public class Band {

    private ArrayList<Mitglied> mitglieder;
    private ArrayList<Song> repertoire;
    private ArrayList<Termin> termine;
    private ArrayList<Ort> orte;
    private ArrayList<Termin> trash;

    /**
     * Konstruktor
     */
    public Band() {
        mitglieder = new ArrayList<Mitglied>();
        repertoire = new ArrayList<Song>();
        termine = new ArrayList<Termin>();
    }

    // Mitglieder
    
    /**
     * Fuegt der Band ein Mitglied hinzu.
     * 
     * @param _m hinzuzufuegendes Mitglied
     */
    public void mitglied_hinzufuegen(Mitglied _m) {
        mitglieder.add(_m);
    }

    /**
     * Entfernt ein Mitglied aus der Band.
     * 
     * @param _m zu entferndenes Mitglied
     */
    public void mitglied_entfernen(Mitglied _m) {
        if (mitglieder.contains(_m)) {
            mitglieder.remove(_m);
        }
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
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Mitglieder innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Mitglied> mitglieder_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Mitglied> mitglieder_liste = new ArrayList<Mitglied>();

        for (Mitglied m : mitglieder) {
            if (m.getVon().before(_bis) && m.getBis().after(_von)) {
                mitglieder_liste.add(m);
            }
        }

        return mitglieder_liste;
    }

    // Repertoire
    
    /**
     * Fuegt dem Repertoire der Band einen Song hinzu.
     * 
     * @param _s hinzuzufuegender Song
     */
    public void song_hinzufuegen(Song _s) {
        repertoire.add(_s);
    }

    /**
     * Entfernt einen Song aus dem Repertoire der Band.
     * 
     * @param _s zu entfernender Song
     */
    public void song_entfernen(Song _s) {
        if (repertoire.contains(_s)) {
            repertoire.remove(_s);
        }
    }

    /**
     * Listet das gesamte Repertoire der Band.
     * 
     * @return Repertoire
     */
    public ArrayList<Song> songs_auflisten() {
        return repertoire;
    }

    /**
     * Listet das Repertoire der Band ab einem gewissen Zeitpunkt.
     * 
     * @param _von gesuchter Zeitpunkt
     * @return Repertoire ab gesuchtem Zeitpunkt
     */
    public ArrayList<Song> songs_auflisten(GregorianCalendar _von) {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();

        for (Song m : repertoire) {
            if (_von.before(m.getVon())) {
                repertoire_liste.add(m);
            }
        }

        return repertoire_liste;
    }

    // Termine
    
    /**
     * Fügt einen Termin hinzu.
     * 
     * @param _t hinzuzufuegender Termin
     */
    public void termin_hinzufuegen(Termin _t) {
        for (int i = 0; i < termine.size(); i++) {
            if (_t.getVon().before(termine.get(i).getVon())) {
                termine.add(i, _t);
                return;
            }
        }
        termine.add(_t);
    }
    
    /**
     * Aendert einen bereits vorhandenen Termin und speichert dessen alte Version.
     * 
     * @param _alt zu aendernder Termin
     * @param _neu 
     */
    public void termin_aendern(Termin _alt, Termin _neu) {
        if(termine.contains(_alt)) {
            
        }
    }
    
    /**
     * Loescht einen Termin
     *
     * @param t die geloeschten Termine
     */
    public void termin_loeschen(Termin t) {
        if(termine.contains(t)) {
            termine.remove(t);
        }
        if(!trash.contains(t)) {
            trash.add(t);
        }
    }
    
    /**
     * Listet alle geloeschten und geaenderten Termine
     * 
     * @return trash geloeschte und geaenderte Termine
     */
    public ArrayList<Termin> trash_auflisten() {
        return trash;
    }

    /**
     * Listet alle Termine innerhalb eines gesuchten Zeitraumes
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Termine innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<? extends Termin> termine_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Termin> termine_liste = new ArrayList<Termin>();

        for (Termin t : termine) {
            if (_von.before(t.getVon()) && _bis.after(t.getBis())) {
                termine_liste.add( t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return termine_liste;
    }

    /**
     * Listet alle Proben innerhalb eines gesuchten Zeitraumes
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Proben innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Probe> proben_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Probe> proben_liste = new ArrayList<Probe>();

        for (Termin t : termine) {
            if (t instanceof Probe && _von.before(t.getVon()) && _bis.after(t.getBis())) {
                proben_liste.add((Probe) t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return proben_liste;
    }

    /**
     * Listet alle Auftritte innerhalb eines gesuchten Zeitraumes
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Auftritte innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Auftritt> auftritte_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Auftritt> auftritte_liste = new ArrayList<Auftritt>();

        for (Termin t : termine) {
            if (t instanceof Auftritt && _von.before(t.getVon()) && _bis.after(t.getBis())) {
                auftritte_liste.add((Auftritt) t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return auftritte_liste;
    }

    /**
     * Summiert die Kosten, die innerhalb eines gesuchten Zeitraumes durch das Mieten der Proberaume entstehen
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Kosten, die innerhalb des gesuchten Zeitraumes entstanden sind
     */
    public int kosten_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        int kosten = 0;
        ArrayList<Probe> proben_kosten = proben_auflisten(_von, _bis);

        for (Probe p : proben_kosten) {
            kosten += p.getMiete();
        }

        return kosten;
    }

    /**
     * Summiert den Umsatz, der innerhalb eines gesuchten Zeitraumes durch Gagen bei den Auftritten verdient wurde
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Umsatz, der innerhalb des gesuchten Zeitraumes erwirtschaftet werden konnte
     */
    public int umsatz_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        int umsatz = 0;
        ArrayList<Auftritt> auftritte_kosten = auftritte_auflisten(_von, _bis);

        for (Auftritt a : auftritte_kosten) {
            umsatz += a.getGage();
        }

        return umsatz;
    }

    /**
     * Summiert den Gewinn, der innerhalb eines gesuchten Zeitraumes erwirtschaftet werden konnte
     * 
     * @param _von Beginn des gesuchten Zeitraumes
     * @param _bis Ende des gesuchten Zeitraumes
     * @return Gewinn, der innerhalb des gesuchten Zeitraumes erwirtschaftet werden konnte
     */
    public int gewinn_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        return umsatz_summieren(_von, _bis) - kosten_summieren(_von, _bis);
    }
    
    /**
     * Fuegt einen Ort hinzu.
     * 
     * @param _o hinzuzufuegender Ort
     */
    public void ort_hinzufuegen(Ort _o) {
        orte.add(_o);
    }

    /**
     * liefert Liste mit Orten, die eine bestimmte Infrastruktur haben.
     *
     * @param plaetze Gesuchte Anzahl an Zuschauerplaetzen(oder 0, wenn egal)
     * 
     * @return die Orte, die die bestimmte Infrastruktur haben. null, wenn kein
     * Ort die Voraussetzungen erfuellt.
     */
    public ArrayList<Ort> finde_ort(int _plaetze) {
        ArrayList<Ort> gef_orte = new ArrayList<Ort>();
        
        for (Ort o : orte) {
        if (o.getPlaetze() >= _plaetze) {
                gef_orte.add(o);
            }
        }

        return gef_orte;
    }
}
