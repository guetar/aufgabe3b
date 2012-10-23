/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Matthias
 */
public class Ort implements Comparable<Ort> {

    private String name;
    private String adresse;
    private int plaetze;

    /**
     * Konstruktor
     *
     * @param name Name des Ortes
     * @param adresse Adresse des Ortes
     * @param zplaetze Anzahl der Zuschauerplätze
     */
    public Ort(String name, String adresse, int plaetze) {
        this.name = name;
        this.adresse = adresse;
        this.plaetze = plaetze;
    }

    /**
     * Kopierkonstruktor
     *
     * @param o zu kopierender Ort
     */
    public Ort(Ort o) {
        name = o.name;
        adresse = o.adresse;
        plaetze = o.plaetze;
    }

    /**
     * Liefert Anzahl der vorhandenen Zuschauerplaetze
     *
     * @return die Anzahl der vorhandenen Zuschauerplätze
     */
    public int getPlaetze() {
        return plaetze;
    }

    @Override
    /**
     * Liefert die Variablen des Ortes als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Adresse und die Anzahl der Zuschauerplaetze
     */
    public String toString() {
        return name + " " + adresse + " " + plaetze;
    }

    @Override
    public int compareTo(Ort o) {
        if (this.name.compareTo(o.name) < 0) {
            return -1;
        } else if (this.name.compareTo(o.name) > 0) {
            return 1;
        }
        return 0;
    }
}
