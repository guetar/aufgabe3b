import java.util.HashMap;
import java.util.HashSet;

public class Abstimmung {

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
    
    
    public boolean abstimmen(Mitglied m, boolean dafuer, String begruendung) {

        if (m != null && mitglieder.contains(m)) {
            dafuers.put(m, dafuer);
            begruendungen.put(m, begruendung);
            return true;
        } else {
            return false;
        }
    }
       
    
    public boolean alleAbgestimmt() {
        return dafuers.size() == mitglieder.size();
    }

    
    public boolean getResult() {
        if (dafuers.size() == mitglieder.size()) {
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
    
    
    public String getResultMessage() {

        if (dafuers.size() == mitglieder.size()) {
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
    
    
    public Termin getTermin() {
        return vorgeschlTermin;
    }
}