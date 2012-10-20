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
     * @param filter "Probe" für Proben, "Sonstige" für alle außer "Proben",
     * null für ALLE Kosten
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Kosten
     */
    public int sumKosten(String filter, GregorianCalendar von, GregorianCalendar bis) {
        int kosten = 0;
        ArrayList<Posten> list = listBilanz(filter, von, bis);

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
     * @param filter "Auftritt" für Auftritte, "Sonstige" für alle außer
     * "Proben", null für ALLE Umsaetze
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Umsaetze
     */
    public int sumUmsatz(String filter, GregorianCalendar von, GregorianCalendar bis) {
        int umsatz = 0;
        ArrayList<Posten> list = listBilanz(filter, von, bis);

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
     * @param filter "Auftritt" für Auftritte, "Probe" für Proben, "Sonstige"
     * für alle außer "Auftritte" und "Proben", null für ALLE Posten
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Summe der Umsaetze
     */
    public int sumGewinn(String filter, GregorianCalendar von, GregorianCalendar bis) {
        int gewinn = 0;
        ArrayList<Posten> list = listBilanz(filter, von, bis);

        for (Posten p : list) {
            gewinn += p.getWert();
        }

        return gewinn;
    }

    /**
     * Erzeugt Liste mit Posten eines bestimmten Typs in einem bestimmten
     * Intervall
     *
     * @param filter "Auftritt" für Auftritte, "Probe" für Proben, "Sonstige"
     * für alle außer "Auftritte" und "Proben", null für ALLE Posten
     * @param von Start des Intervalls
     * @param bis Ende des Intervalls
     * @return Liste mit Posten des Typs "filter" im Zeitraum "von"-"bis"
     */
    public ArrayList<Posten> listBilanz(String filter, GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Posten> list = new ArrayList<>();

        for (Posten p : posten) {
            //Ueberpruefung des Datums
            if (von.before(p.getDatum()) && bis.after(p.getDatum())) {
                if (filter != null) {
                    //Ueberpruefung des Filters
                    if (filter.equals(p.getBeschr()) || (filter.equals("Sonstiges") && !p.getBeschr().equals("Auftritt") && !p.getBeschr().equals("Probe"))) {
                        list.add(p);
                    }
                } else {
                    list.add(p);
                }
            } else if (bis.before(p.getDatum())) {
                break;
            }
        }

        return list;
    }
}
