/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

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
        return alt.setPosten(neu);
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
     * Liefert die Summe aller Kosten eines bestimmten Typs in einem bestimmten
     * Intervall
     *
     * @param showProben true, wenn Proben aufgelistet werden sollen
     * @param showSonstige true, wenn "sonstige"  Posten mit einbezogen  werden sollen
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Kosten
     */
    public int kosten(boolean showProben, boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int kosten = 0;
        TreeSet<Posten> list = bilanzAuflisten(false, showProben, showSonstige, von, bis);

        for (Posten p : list) {
            if (p.getWert() < 0) {
                kosten += p.getWert();
            }
        }

        return Math.abs(kosten);
    }

    /**
     * Liefert die Summe aller Umsaetze eines bestimmten Typs in einem
     * bestimmten Intervall
     *
     * @param showAuftr true, wenn Auftritte mit einbezogen  werden sollen
     * @param showSonstige true, wenn "sonstige"  Posten mit einbezogen  werden sollen
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Umsaetze
     */
    public int umsatz(boolean showAuftr, boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int umsatz = 0;
        TreeSet<Posten> list = bilanzAuflisten(showAuftr, false,showSonstige, von, bis);

        for (Posten p : list) {
            if (p.getWert() > 0) {
                umsatz += p.getWert();
            }
        }

        return umsatz;
    }

    /**
     * Liefert den Gewinn eines bestimmten Typs in einem bestimmten Intervall
     *
     * @param showAuftr true, wenn Auftritte mit einbezogen  werden sollen
     * @param showProben true, wenn Proben aufgelistet werden sollen
     * @param showSonstige true, wenn "sonstige"  Posten mit einbezogen  werden sollen
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Umsaetze
     */
    public int gewinn(boolean showAuftr, boolean showProben, boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int gewinn = 0;
        TreeSet<Posten> list = bilanzAuflisten(showAuftr, showProben, showSonstige, von, bis);

        for (Posten p : list) {
            gewinn += p.getWert();
        }

        return gewinn;
    }
   
    /**
     * Erzeugt Liste mit Posten eines bestimmten Typs in einem bestimmten
     * Intervall
     * @param showAuftr true, wenn Auftritte aufgelistet werden sollen
     * @param showProben true, wenn Proben aufgelistet werden sollen
     * @param showSonstige true, wenn "sonstige"  Posten aufgelistet werden sollen
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Liste mit Posten der angegebenen Typen im Zeitraum "von"-"bis"
     */
    public TreeSet<Posten> bilanzAuflisten(boolean showAuftr, boolean showProben, boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        TreeSet<Posten> list = new TreeSet<Posten>();

        for (Posten p : posten) {
            //Ueberpruefung des Datums
            if (von.before(p.getDatum())&&bis.after(p.getDatum())) {

                    //Ueberpruefung des Filters
                    if ((showAuftr&&p.getBeschr().equals("Auftritt"))||(showProben&&p.getBeschr().equals("Probe"))||(showSonstige&&!p.getBeschr().equals("Auftritt")&&!p.getBeschr().equals("Probe"))) {
                        list.add(p);
                    }
                 
            } else if (bis.before(p.getDatum())) {
                break;
            }
        }

        return list;
    }
}