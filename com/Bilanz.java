/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Matthias
 */
public class Bilanz {

    private ArrayList<Posten> posten;

    /**
     * Konstruktor
     */
    public Bilanz() {
    }

    /**
     * Fügt der Bilanz einen neuen Posten hinzu
     *
     * @param p
     */
    public void addPosten(Posten p) {
        for (int i = 0; i < posten.size(); i++) {
            if (p.getDatum().before(posten.get(i).getDatum())) {
                posten.add(i, p);
                return;
            }
        }
        posten.add(p);
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
    public int sumKosten(boolean showProben,boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int kosten = 0;
        ArrayList<Posten> list = listBilanz(false,showProben,showSonstige, von, bis);

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
    public int sumUmsatz(boolean showAuftr, boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int umsatz = 0;
        ArrayList<Posten> list = listBilanz(showAuftr, false,showSonstige, von, bis);

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
    public int sumGewinn(boolean showAuftr, boolean showProben,boolean showSonstige, GregorianCalendar von, GregorianCalendar bis) {
        int gewinn = 0;
        ArrayList<Posten> list = listBilanz(showAuftr,showProben,showSonstige, von, bis);

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
    public ArrayList<Posten> listBilanz(boolean showAuftr, boolean showProben,boolean showSonstige,GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Posten> list = new ArrayList<Posten>();

        for (Posten p : posten) {
            //Ueberpruefung des Datums
            if (von.before(p.getDatum()) && bis.after(p.getDatum())) {

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
