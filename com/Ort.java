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
    //Invariante: plaetze>=0
    private int plaetze;

    //Vorbedingung: plaetze>=0, name!=null, adresse!=null
    //Nachbedingung: plaetze>=0, name!=null, adresse!=null
    public Ort(String name, String adresse, int plaetze) {
        this.name = name;
        this.adresse = adresse;
        this.plaetze = plaetze;
    }

    public Ort(Ort o) {
        name = o.name;
        adresse = o.adresse;
        plaetze = o.plaetze;
    }

    public int getPlaetze() {
        return plaetze;
    }
    
    public String getName() {
        return name;
    }

    @Override
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
