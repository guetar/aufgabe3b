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
    private LinkedList<Termin> trash;
    
    /**
     * Konstruktor
     */
    public Kalender() {
        this.termine = new TreeSet<Termin>();
        this.trash = new LinkedList<Termin>();
    }
    
    // Termine
    
    /**
     * Fügt einen Termin hinzu.
     * 
     * @param t hinzuzufuegender Termin
     * @return Erfolg
     */
    public boolean terminHinzufuegen(Termin t) {
        return termine.add(t);
    }
    
    /**
     * Aendert einen bereits vorhandenen Termin und speichert dessen alte Version.
     * 
     * @param alt zu aendernder Termin
     * @param neu neuer Termin
     * @return Erfolg
     */
    public Termin terminAendern(Termin alt, Termin neu) {
        if(termine.contains(alt)) {
            
            if(alt instanceof Probe) {
                
                Probe p = (Probe) alt;
                p.setProbe((Probe) neu);
                return p;
                
            } else if (alt instanceof Auftritt) {
                
                Auftritt a = (Auftritt) alt;
                a.setAuftritt((Auftritt) neu);
                return a;
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
        if(termine.contains(t)) {
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
    public Termin terminWiederherstellen(Termin t) {
        Termin alt = t.popFromStack();
        
        if(alt != null) {
            // Eine alte Version des Termins lag am Stack => wird wiederhergestellt
            if(alt instanceof Auftritt) {
                
                Auftritt a_alt = (Auftritt) alt;
                Auftritt t_alt = (Auftritt) t;
                return t_alt.setAuftritt(a_alt);
                
            } else if(alt instanceof Probe) {
                
                Probe p_alt = (Probe) alt;
                Probe t_alt = (Probe) t;
                return t_alt.setProbe(p_alt);
            }
            
        } else {
            
            // keine alte Version vorhanden => Termin muss geloescht worden sein
            if(trash.contains(t)) {
                trash.remove(t);
                termine.add(t);
                return t;
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