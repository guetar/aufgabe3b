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
    private ArrayList<Mitglied> mitglieder;
    private LinkedList<Termin> trash;
    private Bilanz bilanz;
    
    public Kalender(ArrayList<Mitglied> mitglieder, Bilanz bilanz) {
        this.termine = new TreeSet<Termin>();
        this.trash = new LinkedList<Termin>();
        this.bilanz = bilanz;
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
        bilanz.addPosten(new Posten(t));
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
    
    public TreeSet<? extends Termin> termine_auflisten() {
        return termine;
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
}