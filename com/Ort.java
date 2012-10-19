/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Matthias
 */
public class Ort {

    private String name;
    private String adresse;
    private int plaetze;

    /**
     * Konstruktor
     *
     * @param name Name des Ortes
     * @param adresse Adresse des Ortes
     * @param zplaetze Anzahl der Zuschauerplätze
     * @param asteckdosen Anzahl der vorhandenen Steckdosen
     * @param catering true, wenn Catering vorhanden ist
     */
    public Ort(String _name, String _adresse, int _plaetze) {
        name = _name;
        adresse = _adresse;
        plaetze = _plaetze;
    }
    
    /**
     * Kopierkonstruktor
     * 
     * @param _o zu kopierender Ort
     */
    public Ort (Ort _o) {
        name = _o.name;
        adresse = _o.adresse;
        plaetze = _o.plaetze;
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
}
