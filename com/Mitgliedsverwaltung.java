/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author steff
 */
public class Mitgliedsverwaltung {
    
    // HashSets der aktiven und inaktiven mitglieder
    private HashSet<Mitglied> mitglieder;
    private HashSet<Mitglied> ersatzMitglieder;
    
    // HashMap die ein HashSet von Mitgliedern zu einem bestimmten Zeitpunkt zurückgibt
    private HashMap<GregorianCalendar, HashSet<Mitglied>> snapShots;
    
    // Daten an denen Änderungen an der HashSet der Mitglieder vorgenommen worden sind.
    private ArrayList<GregorianCalendar> snapDates;
    
    
    public Mitgliedsverwaltung() {
        mitglieder = new HashSet<Mitglied>();
        snapShots = new HashMap<GregorianCalendar, HashSet<Mitglied>>();
        snapDates = new ArrayList<GregorianCalendar>();
    }
    
    /**
     * Fügt ein Mitglied dem aktiven HashSet mitglieder zu
     * 
     * @param _m hinzuzufügendes Mitglied
     * @param _date Datum des hinzufügens
     * @return "true" wenn Mitglied eingefügt wurde, "false" wenn schon vorhanden
     */
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.add(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok; 
    }
  
     
    /**
     * Entfernt ein Mitglied aus dem aktiven Set
     * 
     * @param _m das zu entferndende Mitglied
     * @param _date Datem des entfernens
     * @return "true" wenn Mitglied entfernt wurde, "false" wenn Mitglied nicht in Set gewesen
     */
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.remove(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok;
    }
    
    
    /**
     * Wechselt den Status eines Mitglieds
     * 
     * @param m das betroffene Mitglied
     * @return "true" wenn Mitglied in Sets vorhanden ist, "false" wenn nicht
     */
    public boolean swapMitglied(Mitglied m, GregorianCalendar date) {
        if (mitglieder.contains(m)) {
            mitglieder.remove(m);
            ersatzMitglieder.add(m);
            makeSnapShot(date);
            return true;
        } else if (ersatzMitglieder.contains(m)) {
            ersatzMitglieder.remove(m);
            mitglieder.add(m);
            makeSnapShot(date);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Kopiert den momentanen Zustand des Sets und verknüpft ihn mit einem Datum
     * 
     * @param date Das entsprechende Datum
     */
    public void makeSnapShot(GregorianCalendar date) {
        // Kopie des Mitglied-Sets nach entfernen des bestimmten Mitglieds
        HashSet<Mitglied> momentMitglieder = new HashSet<Mitglied>(mitglieder);
        // Momentaner Set-Zustand mit Datum verknüpfen
        snapShots.put(date, momentMitglieder);
        // Datum der Änderung in chronologisch geordnete Liste geben
        snapDates.add(date);
    }
    
    /*
     * @return Zustand aktuelles Mitglieds-Set
     */
    public HashSet<Mitglied> mitgliederAuflisten() {
        return mitglieder;
    }
    
    /**
     * Liefert das Mitglieds-Set zu einem bestimmten Datum zurück
     * 
     * @param _date Das Datum des zurückzugebenden Zustands
     * @return Ein Mitglieds-Set zu dem bestimmten Datum
     */
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        
       // Laufvariable
       GregorianCalendar tmpSnapDate = snapDates.get(0);
       
       // Falls erste Änderung nach dem gesuchten Datum liegt
       // null zurückliefern, weil da die Band noch nicht existierte.
       if (tmpSnapDate.after(date)) {
           return null;
       }
       
       // Chronologisches Durchlaufen der Daten an denen Änderungen am Set vorgenommen worden sind
       for (GregorianCalendar snapDate : snapDates) {
           // Liegt das zu suchende Datum innerhalb zweier Daten 
           // wird das Set zum Datum der letzten Änderung ausgegeben
           if(tmpSnapDate.before(date) && snapDate.after(date)) {
               return snapShots.get(tmpSnapDate);
           }
           tmpSnapDate = snapDate;
       }
       
       // Letzte Kontrolle ob das zu suchende Datum jünger ist als letzte
       // Änderung, dann nämlich aktuellen Zustand ausgeben.
       if (tmpSnapDate.before(date)) {
           return mitglieder;
       }
       
       return null;
    }
}
