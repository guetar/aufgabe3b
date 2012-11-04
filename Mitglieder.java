import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;


public class Mitglieder {
    
    /**
     * KLASSE Mitglieder.
     * 
     * 
     * INVARIANTE:
     * >Alle Variablen duerfen nicht NULL sein.
     * >Aus snapShots und snapDates duerfen niemals Werte entfernt werden.
     * Diese Objekte entsprechen einem Protokoll.
     * 
     * GUT:
     * >Die Idee hinter den snapShots. 
     * Wenn eine Aenderung an der Mitgliedszusammensetzung stattfindet 
     * (Hinzufuegen, entfernen, austauschen eines Mitglieds) wird eine Kopie
     * der Zusammensetzung zu diesem Zeitpunkt gemacht. Diese wird dann in die
     * HashMap snapShots gelegt wo sie eindeutig mit dem Datum verknuepft ist.
     * Das Datum wird zum chronologischen durchgehen auch in eine ArrayList gelegt.
     * Wenn nach der Mitgliedszusammensetzung zu einem bestimmten Zeitpunkt gesucht
     * wird, gibt es diejenige aus in deren Intervall sie faellt.
     * Ich finde, dass dies eine elegante und minimale Loesung ist.
     * 
     * 
     * SCHLECHT:
     * >Wenn ein snapShot erstellt wird, wird nicht die chronologische Reihenfolge
     * ueberprueft. 
     */
    private HashSet<Mitglied> mitglieder;
    private HashSet<Mitglied> ersatzMitglieder;
    private HashMap<GregorianCalendar, HashSet<Mitglied>> snapShots;
    private ArrayList<GregorianCalendar> snapDates;
    
    /**
     * KONSTRUKTOR.
     * 
     * 
     * VORBEDINGUNG:
     * Keine. wird leer instanziert.
     * 
     * INVARIANTE:
     * Keine
     * 
     * NACHBEDINGUNG:
     * Die Variablen muessen instanziert worden sein.
     */
    public Mitglieder() {
        mitglieder = new HashSet<Mitglied>();
        ersatzMitglieder = new HashSet<Mitglied>();
        snapShots = new HashMap<GregorianCalendar, HashSet<Mitglied>>();
        snapDates = new ArrayList<GregorianCalendar>();
    }
    
    /**
     * METHODE mitgliedHinzufuegen.
     * 
     * 
     * VORBEDINGUNG:
     * >Parameter Mitglied "m" und "date" duerfen nicht NULL sein
     * >Datum muss hoeher als das letzte sein!
     * 
     * INVARIANTE:
     * >Parameter "m" und "date" duerfen nicht NULL werden.
     * 
     * NACHBEDINGUNG:
     * >Das Mitglied "m" muss in dem HashSet mitglieder eingefuegt worden sein.
     * >Die Methode makeSnapShot muss die Veraenderung erfassen.
     */
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.add(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok; 
    }
    
    /**
     * METHODE ersatzMitgliedHinzufuegen.
     * 
     * 
     * VORBEDINGUNG:
     * >Mitglied "m" darf nicht NULL sein.
     * 
     * NACHBEDINGUNG:
     * >"m" muss in ersatzMitglieder eingefuegt worden sein.
     * 
     * SCHLECHT:
     * >Keine Ueberpruefung ob NULL
     */
    public boolean ersatzMitgliedHinzufuegen(Mitglied m) {
        return ersatzMitglieder.add(m);
    }
     
    /**
     * METHODE mitgliedEntfernen
     * 
     * 
     * VORBEDINGUNG:
     * >Mitglied "m" darf nicht NULL sein, wuerde kein Problem erzeugen wenn mitglieder
     * keinen NULL-Wert enthalten, wenn aber doch dann wird erfolgreich NULL aus mitglieder
     * >Datum muss hoeher als das letzte sein!
     * 
     * INVARIANTE:
     * >Keine nennenswerte
     * 
     * NACHBEDINGUNG:
     * >Mitglied muss aus mitglieder entfernt worden sein.
     * 
     * GUT:
     * >Die Auslagerung des kreieren eines Snapshots an eine eigene Methode, da die mehrmals 
     * aufgerufen wird.
     * 
     * SCHLECHT:
     * >Keine Kontrolle ob der eingehende Paramter NULL ist. in Verbesserung implementieren.
     * >Keine Kontrolle ob das eingehende Datum in chronologischer Reihenfolge ist.
    **/
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.remove(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok;
    }
    
    /**
     * METHODE swapMitglied.
     * 
     * 
     * VORBEDINGUNG:
     * >Zwei Mitglieder werden als Parameter uebergeben, das Ersatzmitglied
     * und das Mitglied mit dem es getauscht werden soll. Diese zwei sollen
     * richtig uebergeben werden und nicht vertauscht.
     * >Nix soll NULL sein.
     * >Datum muss hoeher als das letzte sein!
     * 
     * INVARIANTE:
     * Keinen nennenswerten
     * 
     * NACHBEDINGUNG:
     * >Die Mitglieder sollen erfolgreich vertauscht worden sein.
     * 
    **/
    public boolean swapMitglied(Mitglied mAusErsatz, Mitglied mAusFix, GregorianCalendar date) {
        if (mitglieder.contains(mAusFix)) {
            mitglieder.remove(mAusFix);
            ersatzMitglieder.add(mAusFix);
            mitglieder.add(mAusErsatz);
            makeSnapShot(date);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * METHODE makeSnapShot.
     * 
     * 
     * VORBEDINGUNG:
     * >einehender Parameter Datum muss in chronologischer Reihenfolge sein
     * 
     * INVARIANTE:
     * >keine nennenswerte
     * 
     * NACHBEDINUNG:
     * >Das Datum muss mit dem momentanen Bandzustand verknuepft werden
     * >Das Datum muss in die Liste von Aenderungsdaten eingefuegt werden.
     *  
     * SCHLECHT:
     * >Keine automatische chronologische Sortierung
     */
    private void makeSnapShot(GregorianCalendar date) {
        HashSet<Mitglied> momentMitglieder = new HashSet<Mitglied>(mitglieder);
        snapShots.put(date, momentMitglieder);
        snapDates.add(date);
    }
    
    /**
     * METHODE mitgliederAuflisten.
     * 
     * 
     * Keine nennenswerten VB, IV, NB
     * 
     * GUT:
     * >Alles super!
     * 
     * SCHLECHT:
     * >nix! ;)
     */
    public HashSet<Mitglied> mitgliederAuflisten() {
        return mitglieder;
    }
    
    /**
     * METHODE mitgliederAuflisten.
     * 
     * 
     * VORBEDINGUNGEN:
     * >Parameter "date" muss in chronologischer Reihenfolge sein
     * 
     * INVARIANTE:
     * >"date" darf sich nicht aendern
     * 
     * NACHBEDINGUNG:
     * >Es muss die korrekte Bandzusammensetzung zu dem bestimmten Zeitpunkt 
     * ausgegeben werden.
     * 
     * GUT:
     * >Es wird NULL zurueckgegeben wenn nichts gefunden wird (Also die band
     * zu dem Datum nicht existiert)
     * >Minimaler Code
     */
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        
       GregorianCalendar tmpSnapDate = snapDates.get(0);
       
       if (tmpSnapDate.after(date)) {
           return null;
       }
       
       for (GregorianCalendar snapDate : snapDates) {
           if(tmpSnapDate.before(date) && snapDate.after(date)) {
               return snapShots.get(tmpSnapDate);
           }
           tmpSnapDate = snapDate;
       }
       
       if (tmpSnapDate.before(date)) {
           return mitglieder;
       }
       return null;
    }
}
