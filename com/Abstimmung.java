package com;

import java.util.HashMap;
import java.util.HashSet;

public class Abstimmung {

    /**
     * GUT:
     * Ich glaube, dass der Einsatz von HashMaps und -Sets fuer das Erfassen der 
     * Stimmabgaben ein sehr guter Weg ist. Es gibt kaum Bedingungen die fuer
     * die Funktionalitaet erfuellt sein muessen, das meiste uebernimmt 
     * die HashMap selber, zB fuer folgende Faelle:
     * - Doppelte Abgabe einer Stimme > alte wird einfach ersetzt
     * - null Werte erzeugen keinen Fehler
     * 
     * Auch ist die Syntax und Pragmatik eng verbunden mit der Semantik:
     * - Das Mitglied ist mit der Stimmabgabe und der Begruendung gekoppelt.
     * - EIN Mitglied gibt EINE Stimme ab
     */
    private HashMap<Mitglied, Boolean> dafuers;
    private HashMap<Mitglied, String> begruendungen;
    private HashSet<Mitglied> mitglieder;
    
    /**
     * SCHLECHT:
     * Variable Termin "vorgeschlTermin".
     * Urspruengliche Idee war, dass die Klasse Abstimmung im Falle einer positiven
     * Abstimmung gleich den Termin selber erzeugt und der Band uebergibt.
     * Ich hatte dann einen Denkfehler und dachte mir dass es nicht moeglich ist auf
     * die Variablen einer Klasse ueberhalb zuzugreifen.
     * Deswegen habe Ich die Instanzierung des Termins in test.java gemacht
     * und hier aber vergessen die Variable "vorgeschlTermin" wieder zu entfernen.
     * Sie wird ueberhaupt nicht verwendet.
     * Jetzt bin Ich aber draufgekommen, dass Ich ja in der Klasse Abstimmung sehr wohl
     * einen Termin der Klasse Band uebergeben kann und zwar ueber deren Methode 
     * "terminHinzufuegen".
     * 
     * Verbesserungsvorschlag bei der Methode "getResult".
     */
    private Termin vorgeschlTermin;

    /**
     * Konstruktor.
     * 
     * VORBEDINGUNG:
     * _mitglieder:
     * Das uebergebene HashSet soll auch tatsaechlich die Mitglieder
     * der Band beinhalten.
     * _vorgeschlTermin:
     * ist jetzt noch irrelevant, weil es gar nicht verwendet wird.
     * 
     * INVARIANTE:
     * 
     * 
     * 
     */
    public Abstimmung(HashSet<Mitglied> _mitglieder, Termin _vorgeschlTermin) {
        dafuers = new HashMap<Mitglied, Boolean>();
        begruendungen = new HashMap<Mitglied, String>();
        mitglieder = _mitglieder;
        vorgeschlTermin = _vorgeschlTermin;
    }

    public boolean abstimmen(Mitglied _m, boolean _dafuer, String _begruendung) {

        if (mitglieder.contains(_m)) {
            dafuers.put(_m, _dafuer);
            begruendungen.put(_m, _begruendung);
        } else {
            return false;
        }
        return true;
    }

    public String[] getResult() {

        String result[] = new String[2];

        if (dafuers.size() == mitglieder.size()) {
            result[0] = "1";
            result[1] = "Ergebnis fuer Abstimmung zu: " + vorgeschlTermin;
            
            for (Mitglied m : mitglieder) {
                result[1] += "\n" + m.getName();
                if (dafuers.get(m)) {
                    result[1] += " ist dafuer; '";
                } else {
                    result[1] += " ist dagegen; '";
                    result[0] = "0";
                }
                result[1] += begruendungen.get(m) + "'";
            }
        } else {
            result[0] = "-1";
            result[1] = "Es haben noch nicht alle abgestimmt!";
        }
        return result;
    }
}
