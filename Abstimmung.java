import java.util.HashMap;
import java.util.HashSet;

public class Abstimmung {

    
    //IV: dafuers && begruendungen && mitglieder && vorgeschlTermin != NULL
    private HashMap<Mitglied, Boolean> dafuers;
    private HashMap<Mitglied, String> begruendungen;
    private HashSet<Mitglied> mitglieder;
    private Termin vorgeschlTermin;

    
    public Abstimmung(HashSet<Mitglied> mitglieder, Termin t) {
        this.dafuers = new HashMap<Mitglied, Boolean>();
        this.begruendungen = new HashMap<Mitglied, String>();
        this.mitglieder = mitglieder;
        this.vorgeschlTermin = t;
    }
    
    
    
    //VB: m && begruendung != NULL
    //IV: dafuers und begruendungen duerfen nicht weniger werden
    //NB: m und dafuer und begruendung sollen sinngemaess gespeichert werden
    public boolean abstimmen(Mitglied m, boolean dafuer, String begruendung) {
        if (m != null && begruendung != null && mitglieder.contains(m)) {
            dafuers.put(m, dafuer);
            begruendungen.put(m, begruendung);
            return true;
        } else {
            return false;
        }
    }
       
    
    
    //VB: dafuers && mitglieder != NULL
    //IV: dafuers und mitglieder sollen nicht geaendert werden
    //NB: Rueckgabe ob es soviel dafuers wie mitglieder gibt
    public boolean alleAbgestimmt() {
        return (dafuers != null && mitglieder != null && dafuers.size() == mitglieder.size());
    }

    
    
    //VB: mitglieder und dafuers sollen gleich gross sein
    //IV: mitglieder und dafuers duerfen sich nicht geaendert haben
    //NB: Rueckgabe des Ergebnis
    public boolean getResult() {
        if (dafuers != null && mitglieder != null && dafuers.size() == mitglieder.size()) {
            for (Mitglied m : mitglieder) {
                if (!dafuers.get(m)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    
    
    //VB: dafuers && mitglieder != NULL
    //    Alle sollen bereits abgestimmt worden sein
    //IV: An dafuers und begruendungen darf nichts geaendert werden
    //NB: Rueckgabe des Strings mit den noetigen Informationen 
    //    An dafuers und begruendungen darf nichts geaendert werden
    public String getResultMessage() {

        if (dafuers != null && mitglieder != null && dafuers.size() == mitglieder.size()) {
            String result = "Ergebnis fuer Abstimmung zu: " + vorgeschlTermin;
            boolean findetStatt = true;
            
            for (Mitglied m : mitglieder) {
                result += "\n" + m.getName();
                if (dafuers.get(m)) {
                    result += " ist dafuer; '";
                } else {
                    result += " ist dagegen; '";
                    findetStatt = false;
                }
                result += begruendungen.get(m) + "'";
            }
            if(findetStatt) {
                result += "\nDer Termin findet statt.";
            }else {
                result += "\nDer Termin findet nicht statt.";
            }
            return result;
        } else {
            return "Es haben noch nicht alle abgestimmt!";
        }
    }
    
    
    
    //VB: vorgeschlTermin != NULL
    //IV: vorgeschlTermin darf nicht geaendert werden
    //NB: vorgeschlTermin darf nicht geaendert werden
    public Termin getTermin() {
        return vorgeschlTermin;
    }
}