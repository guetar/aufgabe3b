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
 * @author Matthias
 */
public class Bilanz {

    private TreeSet<Posten> posten;
    private LinkedList<Posten> trash;

    /**
     * Konstruktor
     */
    public Bilanz() {
        this.posten = new TreeSet<Posten>();
        this.trash = new LinkedList<Posten>();
    }
    
    /**
     * Gibt den Posten fuer den betreffenden Termin zurueck
     * 
     * @param t Termin
     * @return Posten
     */
    public Posten getPosten(Termin t) {
        Posten p = new Posten(t);
        if(posten.contains(p)) {
            return p;
        }
        return null;
    }
    
    /**
     * Ueberprueft, ob der uebergebene Posten existiert
     * 
     * @param p zu ueberpruefender Posten
     * @return dessen Existenz
     */
    public boolean postenExistiert(Posten p) {
        return posten.contains(p);
    }

    /**
     * Fuegt der Bilanz einen neuen Posten hinzu
     *
     * @param p Bilanzposten
     * @return Erfolg
     */
    public boolean postenHinzufuegen(Posten p) {
        return posten.add(p);
    }
    
    public Posten postenAendern(Posten alt, Posten neu) {
        for(Posten p : posten) {
            if(p == alt) {
                posten.remove(p);
                p.setPosten(neu);
                posten.add(p);
                return p;
            }
        }
        return null;
    }
    
    /**
     * Loescht einen Posten
     *
     * @param p der zu loeschende Posten
     * @return Erfolg
     */
    public boolean postenLoeschen(Posten p) {
        if(posten.contains(p)) {
            trash.add(p);
            posten.remove(p);
            return true;
        }
        return false;
    }
    
    /**
     * Stellt einen Posten wieder her
     * 
     * @param p wiederherzustellender Posten
     * @return 
     */
    public Posten postenWiederherstellen(Posten p) {
        Posten alt = p.popFromStack();
        
        if(alt != null) {
            return alt.setPosten(p);
            
        } else {
            
            // keine alte Version vorhanden => Termin muss geloescht worden sein
            if(trash.contains(p)) {
                trash.remove(p);
                posten.add(p);
                return p;
            }
        }
        return null;
    }
   
    /**
     * Summiert alle Posten in dem angegebenen Zeitraum nach den Kriterien
     * 
     * @param showAuftr true, wenn Auftritte aufgelistet werden sollen
     * @param showProben true, wenn Proben aufgelistet werden sollen
     * @param showSonstige true, wenn "sonstige"  Posten aufgelistet werden sollen
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Posten
     */
    public int postenSummieren(boolean showAuftr, boolean showProben, boolean showEinnahmen, boolean showAusgaben, GregorianCalendar von, GregorianCalendar bis) {
        int sum = 0;
        ArrayList<Integer> filter = new ArrayList<Integer>();
        if (showAuftr)       { filter.add(1); }
        if (showProben)      { filter.add(2); }
        if (showEinnahmen)   { filter.add(3); }
        if (showAusgaben)    { filter.add(4); }        
        
        for (Posten p : posten) {
            //Ueberpruefung des Datums
            if (von.before(p.getDatum())&&bis.after(p.getDatum())) {

                    //Ueberpruefung des Filters
                    if (filter.contains(p.getKategorie())) {
                        sum += p.getWert();
                    }
                 
            } else if (bis.before(p.getDatum())) {
                break;
            }
        }

        return sum;
    }    
    
    /**
     * Gibt eine Liste aller Posten in dem angegebenen Zeitraum nach den Kriterien aus
     * 
     * @param showAuftr
     * @param showProben
     * @param showSonstige
     * @param von
     * @param bis
     * @return 
     */
    public TreeSet<Posten> postenAuflisten(boolean showAuftr, boolean showProben, boolean showEinnahmen, boolean showAusgaben, GregorianCalendar von, GregorianCalendar bis) {
       TreeSet<Posten> ergebnis = new TreeSet<Posten>();
       ArrayList<Integer> filter = new ArrayList<Integer>();
       if (showAuftr)       { filter.add(1); }
       if (showProben)      { filter.add(2); }
       if (showEinnahmen)   { filter.add(3); }
       if (showAusgaben)    { filter.add(4); }
       
       for (Posten p : posten) {
            //Ueberpruefung des Datums
            if (von.before(p.getDatum())&&bis.after(p.getDatum())) {

                    //Ueberpruefung des Filters
                    if (filter.contains(p.getKategorie())) {
                        ergebnis.add(p);
                    }
                 
            } else if (bis.before(p.getDatum())) {
                break;
            }
        }
       
       return ergebnis;
    }
}
