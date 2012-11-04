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

    //Invariante: posten ist chronologisch nach Datum sortiert
    private TreeSet<Posten> posten;
    private LinkedList<Posten> trash;

    //Nachbedingung: posten!=null, trash!=null
    public Bilanz() {
        this.posten = new TreeSet<Posten>();
        this.trash = new LinkedList<Posten>();
    }
    
    //Vorbedingung: t!=null
    public Posten getPosten(Termin t) {
        Posten p = new Posten(t);
        if(posten.contains(p)) {
            return p;
        }
        return null;
    }
    
    public boolean postenExistiert(Posten p) {
        return posten.contains(p);
    }

    //Nachbedingung: wenn true zurückgegeben wird, wird p zu posten hinzugefuegt
    public boolean postenHinzufuegen(Posten p) {
        return posten.add(p);
    }
    
    //Nachbedingung: alt in posten wird durch neu ersetzt
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
    
    //Nachbedingung: wenn true zurückgegeben wird, wird p aus posten entfernt
    // und zu trash hinzugefuegt
    public boolean postenLoeschen(Posten p) {
        if(posten.contains(p)) {
            trash.add(p);
            posten.remove(p);
            return true;
        }
        return false;
    }
    
    //Vorbedingung: p!=null
    public Posten postenWiederherstellen(Posten p) {
        Posten alt = p.popFromStack();
        
        if(alt != null) {
            return alt.setPosten(p);
            
        } else {
                      
            if(trash.contains(p)) {
                trash.remove(p);
                posten.add(p);
                return p;
            }
        }
        return null;
    }
   
    //Vorbedingung: von!=null,bis!=null. "von" muss chronologisch vor "bis" sein
    //Schlecht: Haette postenAuflisten() benutzen koennen
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
    
    //Vorbedingung: von!=null,bis!=null. "von" muss chronologisch vor "bis" sein
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
