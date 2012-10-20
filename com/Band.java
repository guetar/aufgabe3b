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
    private TreeSet<Termin> termine;
    private LinkedList<Termin> trash;
    private Bilanz bilanz;

    /**
     * Konstruktor
     */
    public Band() {
        mitglieder = new ArrayList<Mitglied>();
        repertoire = new ArrayList<Song>();
        termine = new TreeSet<Termin>();
        trash = new LinkedList<Termin>();
        bilanz=new Bilanz();
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
     * Fügt einen neuen Bilanzposten hinzu
     *
     * @param p hinzuzufuegender Posten
     * @return Erfolg
     */
    public Boolean posten_hinzufuegen(Posten p) {
        return bilanz.addPosten(p);
    }
    
    // Termine
    
    /**
     * Fügt einen Termin hinzu.
     * 
     * @param t hinzuzufuegender Termin
     * @return Erfolg
     */
    public Boolean termin_hinzufuegen(Termin t) {
        int wert = (t instanceof Auftritt) ? ((Auftritt) t).getGage() : ((Probe) t).getMiete();
        String beschr = (t instanceof Auftritt) ? "Auftritt" : "Probe";
        bilanz.addPosten(new Posten(wert, beschr, t.getDatum(), t));
        return termine.add(t);
    }
    
    /**
     * Aendert einen bereits vorhandenen Termin und speichert dessen alte Version.
     * 
     * @param alt zu aendernder Termin
     * @param neu neuer Termin
     * @return Erfolg
     */
    public Boolean termin_aendern(Termin alt, Termin neu) {
        if(termine.contains(alt)) {
            
            if(alt instanceof Probe) {
                
                Probe p = (Probe) alt;
                p.setProbe((Probe) neu);
                
                for(Mitglied m : mitglieder) {
                    m.message("Folgende Probe wurde geändert: " + p.toString());
                }
                
            } else if (alt instanceof Auftritt) {
                
                Auftritt a = (Auftritt) alt;
                a.setAuftritt((Auftritt) neu);
                
                for(Mitglied m : mitglieder) {
                    m.message("Folgender Auftritt wurde geändert: " + a.toString());
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Loescht einen Termin
     *
     * @param t die geloeschten Termine
     * @return Erfolg
     */
    public Boolean termin_loeschen(Termin t) {
        if(termine.contains(t)) {
            trash.add(t);
            termine.remove(t);
            
            for(Mitglied m : mitglieder) {
                m.message("Folgender Termin wurde abgesagt: " + t.toString());
            }
            return true;
        }
        return false;
    }
    
    /**
     * Stellt einen Termin wieder her
     * 
     * @param t der wiederherzustellende Termin
     * @return Erfolg
     */
    public Boolean termin_wiederherstellen(Termin t) {
        Termin alt = t.popFromStack();
        
        if(alt != null) {
            // Eine alte Version des Termins lag am Stack => wird wiederhergestellt
            t.setTermin(alt);
            return true;
        } else {
            // keine alte Version vorhanden => Termin muss geloescht worden sein
            if(trash.contains(t)) {
                trash.remove(t);
                termine.add(t);
                return true;
            }
        }
        return false;
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
