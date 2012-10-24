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
public class Kalender {

    private TreeSet<Termin> termine;
    private TreeSet<Ort> orte;
    private LinkedList<Termin> trash;

    /**
     * Konstruktor
     */
    public Kalender() {
        this.termine = new TreeSet<Termin>();
        this.trash = new LinkedList<Termin>();
        this.orte = new TreeSet<Ort>();
    }

    //Orte
    /**
     * Fuegt einen Ort hinzu
     *
     * @param o hinzuzufuegender Ort
     * @return Erfolg
     */
    public boolean ortHinzufuegen(Ort o) {
        return orte.add(o);
    }

    /**
     * liefert Liste mit Orten, die eine bestimmte Infrastruktur haben.
     *
     * @param plaetze Gesuchte Anzahl an Zuschauerplaetzen(oder 0, wenn egal)
     *
     * @return die Orte, die die bestimmte Infrastruktur haben. Leere Liste,
     * wenn kein Ort die Voraussetzungen erfuellt.
     */
    public ArrayList<Ort> findeOrt(int plaetze) {
        ArrayList<Ort> gefOrte = new ArrayList<Ort>();

        for (Ort o : orte) {

            if (o.getPlaetze() >= plaetze) {
                gefOrte.add(o);
            }
        }

        return gefOrte;
    }

    // Termine
    /**
     * Fuegt einen Termin hinzu.
     *
     * @param t hinzuzufuegender Termin
     * @return Erfolg
     */
    public boolean terminHinzufuegen(Termin t) {
        return termine.add(t);
    }

    /**
     * Aendert einen bereits vorhandenen Termin und speichert dessen alte
     * Version.
     *
     * @param alt zu aendernder Termin
     * @param neu neuer Termin
     * @return Erfolg
     */
    public Termin terminAendern(GregorianCalendar alt, Termin neu) {
        
        for(Termin t: termine) {
            if(t.getDatum().equals(alt)) {
                if(t instanceof Probe) {
                
                    termine.remove(t);
                    Probe p = (Probe) t;
                    Probe p_neu = p.setProbe((Probe) neu);
                    termine.add(p_neu);
                    return p_neu;

                } else if (t instanceof Auftritt) {

                    Auftritt a = (Auftritt) t;
                    a.setAuftritt((Auftritt) neu);
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Loescht einen Termin
     *
     * @param t der zu loeschende Termin
     * @return Erfolg
     */
    public boolean terminLoeschen(Termin t) {
        if (termine.contains(t)) {
            trash.add(t);
            termine.remove(t);
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
    public Termin terminWiederherstellen(GregorianCalendar datum) {
        for(Termin t : termine) {
            if(t.getDatum().equals(datum)) {
                Termin alt = t.popFromStack();
        
                if(alt != null) {
                    // Eine alte Version des Termins lag am Stack => wird wiederhergestellt
                    if(alt instanceof Probe) {

                        termine.remove(t);
                        Probe p_alt = (Probe) alt;
                        Probe t_alt = (Probe) t;
                        t_alt.setProbe(p_alt);
                        termine.add(t_alt);
                        return t_alt;

                    } else if(alt instanceof Auftritt) {

                        termine.remove(t);
                        Auftritt a_alt = (Auftritt) alt;
                        Auftritt t_alt = (Auftritt) t;
                        t_alt.setAuftritt(a_alt);
                        termine.add(t_alt);
                        return t_alt;
                    }
                }
            }
        }
        
        for(Termin t : trash) {
            if(t.getDatum().equals(datum)) {
                // keine alte Version vorhanden => Termin muss geloescht worden sein
                if(trash.contains(t)) {
                    trash.remove(t);
                    termine.add(t);
                    return t;
                }
            }
        }
        
        return null;
    }

    public TreeSet<? extends Termin> termineAuflisten() {
        return termine;
    }

    /**
     * Listet alle Termine innerhalb eines gesuchten Zeitraumes
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Termine innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<? extends Termin> termineAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Termin> termine_liste = new ArrayList<Termin>();

        for (Termin t : termine) {
            if (von.before(t.getDatum()) && bis.after(t.getDatum())) {
                termine_liste.add(t);
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
    public ArrayList<Probe> probenAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Probe> probenListe = new ArrayList<Probe>();

        for (Termin t : termine) {
            if (t instanceof Probe && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                probenListe.add((Probe) t);
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return probenListe;
    }

    /**
     * Listet alle Auftritte innerhalb eines gesuchten Zeitraumes
     *
     * @param von Beginn des gesuchten Zeitraumes
     * @param bis Ende des gesuchten Zeitraumes
     * @return Auftritte innerhalb des gesuchten Zeitraumes
     */
    public ArrayList<Auftritt> auftritteAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Auftritt> auftritteListe = new ArrayList<Auftritt>();

        for (Termin t : termine) {
            if (t instanceof Auftritt && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                auftritteListe.add((Auftritt) t);
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return auftritteListe;
    }

    /**
     * Listet alle geloeschten und geaenderten Termine
     *
     * @return trash geloeschte und geaenderte Termine
     */
    public LinkedList<Termin> trashAuflisten() {
        return trash;
    }
}