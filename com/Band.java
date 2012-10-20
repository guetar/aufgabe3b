/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author guetar
 */
public class Band {

    private ArrayList<Mitglied> mitglieder;
    private ArrayList<Song> repertoire;
    private ArrayList<Termin> termine;
    private ArrayList<Termin> trash;
    private Bilanz bilanz;

    /**
     * Konstruktor
     */
    public Band() {
        mitglieder = new ArrayList<Mitglied>();
        repertoire = new ArrayList<Song>();
        termine = new ArrayList<Termin>();
        trash = new ArrayList<Termin>();
        bilanz=new Bilanz();
    }

    // Mitglieder
    
    /**
     * Fuegt der Band ein Mitglied hinzu.
     * 
     * @param m hinzuzufuegendes Mitglied
     */
    public void mitglied_hinzufuegen(Mitglied m) {
        mitglieder.add(m);
    }

    /**
     * Entfernt ein Mitglied aus der Band.
     * 
     * @param m zu entferndenes Mitglied
     */
    public void mitglied_entfernen(Mitglied m) {
        if (mitglieder.contains(m)) {
            mitglieder.remove(m);
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
     */
    public void song_hinzufuegen(Song s) {
        repertoire.add(s);
    }

    /**
     * Entfernt einen Song aus dem Repertoire der Band.
     * 
     * @param s zu entfernender Song
     */
    public void song_entfernen(Song s) {
        if (repertoire.contains(s)) {
            repertoire.remove(s);
        }
    }

    /**
     * 
     * @return Repertoire
     */
    public ArrayList<Song> songs_auflisten() {
        return repertoire;
    }

    /**
     * Listet das Repertoire der Band ab einem gewissen Zeitpunkt.
     * 
     * @param von gesuchter Zeitpunkt
     * @return Repertoire ab gesuchtem Zeitpunkt
     */
    public ArrayList<Song> songs_auflisten(GregorianCalendar von) {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();

        for (Song m : repertoire) {
            if (von.before(m.getVon())) {
                repertoire_liste.add(m);
            }
        }

        return repertoire_liste;
    }

     /**
     * Fügt einen Posten hinzu.
     * 
     * @param _p hinzuzufuegender Posten
     */
    public void posten_hinzufuegen(Posten _p) {
        bilanz.addPosten(_p);
    }   
    
    // Termine
    
    /**
     * Fügt einen Termin und einen Posten hinzu.
     * 
     * @param t hinzuzufuegender Termin
     */
    public void termin_hinzufuegen(Termin t) {
        termine.add(t);
    }
    
    /**
     * Aendert einen bereits vorhandenen Termin und speichert dessen alte Version.
     * 
     * @param alt zu aendernder Termin
     * @param neu 
     */
    public void termin_aendern(Termin alt, Termin neu) {
        if(termine.contains(alt)) {
            termine.remove(alt);
            alt = alt.setTermin(neu);
            termine.add(alt);
        }
    }
    
    /**
     * Loescht einen Termin
     *
     * @param t die geloeschten Termine
     */
    public void termin_loeschen(Termin t) {
        if(termine.contains(t)) {
            trash.add(t);
            termine.remove(t);
        }
    }
    
    /**
     * Stellt einen Termin wieder her
     * 
     * @param t der wiederherzustellende Termin
     */
    public void termin_wiederherstellen(Termin t) {
        if(t.popFromStack() == null) {
            
            // keine alte Version vorhanden =>
            // Termin muss geloescht worden sein
            if(trash.contains(t)) {
                trash.remove(t);
                termine.add(t);
            }
        }
    }

    /**
     * Listet alle Termine innerhalb eines gesuchten Zeitraumes
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Termine innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<? extends Termin> termine_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Termin> termine_liste = new ArrayList<Termin>();

        for (Termin t : termine) {
            if (von.before(t.getDatum()) && bis.after(t.getDatum())) {
                termine_liste.add( t);
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return termine_liste;
    }

    /**
     * Listet alle Proben innerhalb eines gesuchten Zeitraumes
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Proben innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Probe> proben_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Probe> proben_liste = new ArrayList<Probe>();

        for (Termin t : termine) {
            if (t instanceof Probe && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                proben_liste.add((Probe) t);
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return proben_liste;
    }

    /**
     * Listet alle Auftritte innerhalb eines gesuchten Zeitraumes
     * 
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Auftritte innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Auftritt> auftritte_auflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Auftritt> auftritte_liste = new ArrayList<Auftritt>();

        for (Termin t : termine) {
            if (t instanceof Auftritt && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                auftritte_liste.add((Auftritt) t);
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return auftritte_liste;
    }
    
    /**
     * Listet alle geloeschten und geaenderten Termine
     * 
     * @return trash geloeschte und geaenderte Termine
     */
    public LinkedList<Termin> trash_auflisten() {
        return trash;
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
        ArrayList<Probe> proben_kosten = proben_auflisten(von, bis);

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
        ArrayList<Auftritt> auftritte_kosten = auftritte_auflisten(von, bis);

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
        
        for (Termin t : termine) {
            Ort o = t.getOrt();

            if (o.getPlaetze() >= plaetze) {
                    gef_orte.add(o);
            }
        }

        return gef_orte;
    }
}
