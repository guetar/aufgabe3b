package com;

import java.util.HashMap;
import java.util.HashSet;

public class Abstimmung {

    /**
     * KLASSE ABSTIMMUNG.
     * 
     * 
     * INVARIANTE:
     * >Das HashSet mitglieder beinhaltet die Mitglieder einer Band und wird 
     * von ihr der Klasse Abstimmung bei ihrer Instanzierung uebergeben.
     * Dieses Set darf sich nach Instanzierung nicht mehr veraendern.
     * Eine Abstimmung wurde ueber bestimmte Mitglieder erstellt und daher
     * sollen sich diese auch nicht mehr aendern.
     * 
     * >Die HashMaps dafuers und begruendunge muessen immer dieselben Mitglieder 
     * enthalten! Wenn ein Mitglied seine Stimme abgibt, dann wird er mit seiner
     * Entscheidung UND Begruendung gleichzeitig gespeichert.
     * 
     * 
     * GUT:
     * >Ich glaube, dass der Einsatz von HashMaps und -Sets fuer das Erfassen der 
     * Stimmabgaben ein sehr guter Weg ist.
     * Es gibt kaum Bedingungen die fuer die Funktionalitaet erfuellt sein 
     * muessen, das meiste uebernimmt die HashMap selber, zB fuer folgende Faelle:
     * - Doppelte Abgabe einer Stimme > alte wird einfach ersetzt
     * - null Werte werfen keine Exception. (Jedoch ergeben sie eine
     * semantische Ungueltigkeit, mehr dazu bei der Methode "getResult"
     * 
     * >Syntax und Pragmatik sind eng verbunden mit der Semantik:
     * - Das Mitglied ist mit der Stimmabgabe und der Begruendung gekoppelt.
     * - EIN Mitglied gibt genau EINE Stimme und EINE Begruendung ab.
     * 
     * >Volle Funktionalitaet bei minimalen Code:
     * Es gibt nur Konstruktor, Stimme abgeben und Resultat holen.
     * 
     * 
     * SCHLECHT:
     * >Variable Termin "vorgeschlTermin".
     * Urspruengliche Idee war, dass die Klasse Abstimmung im Falle einer positiven
     * Abstimmung gleich den Termin selber erzeugt und der Band uebergibt.
     * Ich hatte dann einen Denkfehler und dachte mir dass es nicht moeglich ist auf
     * die Variablen einer Klasse ueberhalb zuzugreifen.
     * Deswegen habe Ich die Instanzierung des Termins in test.java gemacht
     * und hier aber vergessen die Variable "vorgeschlTermin" wieder zu entfernen.
     * Sie wird ueberhaupt nicht verwendet.
     * 
     * Jetzt bin Ich aber draufgekommen, dass Ich ja in der Klasse Abstimmung sehr wohl
     * einen Termin der Klasse Band uebergeben kann und zwar ueber deren Methode 
     * "terminHinzufuegen".
     * 
     * Verbesserungsvorschlag bei der Methode "getResult".
     */
    private HashMap<Mitglied, Boolean> dafuers;
    private HashMap<Mitglied, String> begruendungen;
    private HashSet<Mitglied> mitglieder;
    private Termin vorgeschlTermin;

    
    
    
    
    
    /**
     * KONSTRUKTOR.
     * 
     * 
     * VORBEDINGUNG:
     * _mitglieder:
     * Das uebergebene HashSet soll auch tatsaechlich die Mitglieder
     * der Band beinhalten.
     * Soll auch nicht NULL sein.
     * _vorgeschlTermin:
     * ist jetzt noch irrelevant, weil es gar nicht verwendet wird.
     * 
     * NACHBEDINGUNG:
     * Das HashSet soll nach erzeugen der Abstimmung die mitglieder der band
     * gespeichert haben und nie mehr veraendert werden.
     */
    public Abstimmung(HashSet<Mitglied> _mitglieder, Termin _vorgeschlTermin) {
        dafuers = new HashMap<Mitglied, Boolean>();
        begruendungen = new HashMap<Mitglied, String>();
        mitglieder = _mitglieder;
        vorgeschlTermin = _vorgeschlTermin;
    }
    
    
    
    
    
    
   

    /**
     * METHODE ABSTIMMEN.
     * 
     * 
     * VORBEDINGUNG:
     * Mitglied _m darf nicht NULL sein.
     * _m soll Mitglied der Band sein, die die Abstimmung erstellt hat.
     * Wenn nicht wird seine Stimme nicht gespeichert und die Methode
     * retourniert ein "false".
     * 
     * INVARIANTE:
     * Die eingehende Argumente duerfen sich nicht aendern.
     * 
     * NACHBEDINGUNG:
     * _m hat seine Stimme abgegeben und muss in beiden HashSets "dafuers" und
     * "begruendungen" eingefuegt worden sein.
     * 
     * GUT:
     * Minimaler und einfacher Code der seinen Zweck und semantisch voll erfuellt.
     * 
     * SCHLECHT:
     * return true sollte aufgrund der Semantik in den if-Zweig rein und nicht
     * ausserhalb.
     * 
     */
    public boolean abstimmen(Mitglied _m, boolean _dafuer, String _begruendung) {

        if (mitglieder.contains(_m)) {
            dafuers.put(_m, _dafuer);
            begruendungen.put(_m, _begruendung);
        } else {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    /**
     * METHODE GETRESULT.
     * 
     * 
     * VORBEDINGUNG:
     * Keine. Die Methode wird ohne Parameteruebergabe aufgerufen.
     * 
     * INVARIANTE:
     * Die Variablen dafuers, begruendungen und mitglieder duerfen sich waehrend
     * der Methode nicht aendern.
     * 
     * NACHBEDINUNG:
     * Das zurueckgegebene String-Array muss immer folgende Struktur einhalten:
     * [0]: Ist 1 wenn alle fuer den Termin sind, 0 wenn nicht, -1 wenn noch nicht
     * alle abgestimmt haben.
     * [1]: Textuelle Beschreibung der Abstimmung die beinhaltet wer wie
     * abgestimmt hat und ob der Termin statt finden wird oder nicht oder noch
     * nicht alle abgestimmt haben.
     * 
     * 
     * GUT:
     * >Die Wahl eines String-Arrays. Es gibt drei Moeglichkeiten von Werten die
     * beim Methodenaufruf zurueckgegeben werden koennen:
     * -Alle dafuer
     * -nicht alle dafuer
     * -nicht alle abgestimmt
     * Wegen diesen drei Werten reicht ein boolean nicht aus, daher fiel meine 
     * Wahl auf Informationsweitergabe in Textform.
     * 
     * 
     * SCHLECHT:
     * >Im Falle einer positiv erfolgten Abstimmung sollte der vorgeschlagene Termin
     * automatisch in den Terminplaner der Band eingetragen werden.
     * Dies wuerde uber den Methodenaufruf "terminHinzufuegen" der Klasse Band
     * geschehen.
     * >Wenn ein NULL Wert in die dafuers-HashMap gespeichert
     * wird kann die Groesse der HashMap nicht mehr semantisch gueltig
     * mit der der mitglieder uebereinstimmen.
     * -Verbesserungsvorschlag: Falls vorhanden, NULL-Wert entfernen.
     */
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
