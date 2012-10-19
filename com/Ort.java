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
    private int zplaetze;
    private int asteckdosen;
    private boolean catering;

    /**
     * erzeugt ein neues Ort Objekt
     *
     * @param name Name des Ortes
     * @param adresse Adresse des Ortes
     * @param zplaetze Anzahl der Zuschauerplätze
     * @param asteckdosen Anzahl der vorhandenen Steckdosen
     * @param catering true, wenn Catering vorhanden ist
     */
    public Ort(String name, String adresse, int zplaetze, int asteckdosen, boolean catering) {
        this.name = name;
        this.adresse = adresse;
        this.asteckdosen = asteckdosen;
        this.zplaetze = zplaetze;
        this.catering = catering;
    }

    /**
     *
     * @return true, wenn der Ort ein Catering besitzt
     */
    public boolean hatCatering() {
        return catering;
    }

    /**
     * Liefert Anzahl der vorhandenen Steckdosen
     *
     * @return die Anzahl der vorhandenen Steckdosen
     */
    public int getAnzSteckdosen() {
        return asteckdosen;
    }
//push test
    
    /**
     * Liefert Anzahl der vorhandenen Zuschauerplätze
     *
     * @return die Anzahl der vorhandenen Zuschauerplätze
     */
    public int getAnzZuschauerPl() {
        return zplaetze;
    }

    @Override
    /**
     * Liefert die Elemente des Ortes als String getrennt durch Leerzeichen in
     * der Reihenfolge: Name, Adresse, Anzahl der Zuschauerplätze, Anzahl der
     * Steckdosen, besitzt Catering
     */
    public String toString() {
        return name + " " + adresse + " " + zplaetze + " " + asteckdosen + " " + (catering ? "hat Catering" : "hat kein Catering");
    }
}
