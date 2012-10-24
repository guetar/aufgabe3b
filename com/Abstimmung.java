/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author steff
 */
public class Abstimmung {
    private HashMap<Mitglied, Boolean> dafuers; //beinhaltet die Entscheidungen der Mitglieder
    private HashMap<Mitglied, String> begruendungen; // Begruendung der Mitglieder
    private HashSet<Mitglied> mitglieder; //Alle zur Abstimmung teilnehmenden Mitglieder
    private Termin vorgeschlTermin; //Der vorgeschlagene Termin
    
    
    /**
     * Initialisierung 
     */
    public Abstimmung(HashSet<Mitglied> _mitglieder, Termin _vorgeschlTermin) {
        dafuers = new HashMap<Mitglied, Boolean>();
        begruendungen = new HashMap<Mitglied, String>();
        mitglieder = _mitglieder;
        vorgeschlTermin = _vorgeschlTermin;
    }
    
    /**
     * Methode abstimmen laesst ein einzelnes Mitglied abstimmen und
     * speichert seine Entscheidung wie auch Begruendung
     */
    public boolean abstimmen(Mitglied _m, boolean _dafuer, String _begruendung) {
        
        if (mitglieder.contains(_m)) {
            dafuers.put(_m, _dafuer);
            begruendungen.put(_m, _begruendung);
        }
        else {
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Liefert das Ergebnis der Abstimmung
     */
    public String[] getResult() {
        
        String result[] = new String[2];   
        /** result ist der Ergebnis-String, der speichert an 
        * [0]: "1" wenn alle dafuer sind, "0" wenn mindestens einer dagegegen, "-1" wenn noch nicht alle abgestimmt haben
        * [1]: Textausgabe des Ergebnis mit einzelnen votes und Begruendungen
        **/  
        
        // Wenn die Groessen der HashMap ueber Abstimmungen die der Anzahl der Mitglieder entsprechen
        // haben alle abgestimmt, ansonsten wird "-1" an result[0] des Ergebnis-String gespeichert.
        if (dafuers.size() == mitglieder.size()) {
            // Standardmaessig wird erwartet, dass alle dafuer sind,
            // ist nur einer dagegen wird result[0] = "0"
            result[0] = "1";
            result[1] = "Ergebnis fuer Abstimmung zu: " + vorgeschlTermin;
            
            // Durchlaufen aller Mitglieder
            for (Mitglied m : mitglieder) {
                result[1] += "\n" + m.getName();
                if (dafuers.get(m)) {
                    result[1] += " ist dafuer; '";
                }
                else {
                    // eine Gegenstimme reicht und der Termin findet nicht statt
                    result[1] += " ist dagegen; '";
                    result[0] = "0";
                }
                result[1] += begruendungen.get(m) + "'";
            }
        }
        else {
            result[0] = "-1";
            result[1] = "Es haben noch nicht alle abgestimmt!";
        }
        
        return result;
    }
}
