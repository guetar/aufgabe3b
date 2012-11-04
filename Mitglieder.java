import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;


public class Mitglieder { 
    
    private HashSet<Mitglied> mitglieder;
    private HashSet<Mitglied> ersatzMitglieder;
    private HashMap<GregorianCalendar, HashSet<Mitglied>> snapShots;

    
    public Mitglieder() {
        mitglieder = new HashSet<Mitglied>();
        ersatzMitglieder = new HashSet<Mitglied>();
        snapShots = new HashMap<GregorianCalendar, HashSet<Mitglied>>();
    }

    
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.add(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok; 
    }

    
    public boolean ersatzMitgliedHinzufuegen(Mitglied m) {
        return ersatzMitglieder.add(m);
    }

    
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar date) {
        boolean ok = mitglieder.remove(m);
        if (ok) {
            makeSnapShot(date);
        }
        return ok;
    }

    
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

    
    private void makeSnapShot(GregorianCalendar date) {
        HashSet<Mitglied> momentMitglieder = new HashSet<Mitglied>(mitglieder);
        snapShots.put(date, momentMitglieder);
    }

    
    public HashSet<Mitglied> mitgliederAuflisten() {
        return new HashSet<Mitglied>(mitglieder);
    }
    
  
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        
        GregorianCalendar tmp = null;

        for (GregorianCalendar d : snapShots.keySet()) {
            
            if (date.before(d) && tmp == null) {
                tmp = d;
            }    
            else if(date.before(d) && d.before(tmp)){
                tmp = d;
            }
        }
       
        if (tmp == null) {
            return new HashSet<Mitglied>(mitglieder);
        }
        else {
            return new HashSet<Mitglied>(snapShots.get(tmp));
        }
    }
}
