import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TreeSet;


public class Mitglieder {
    
    
    
    //IV: mitglieder && snapShots != NULL, 
    //    aus snapShots niemals Elemente entfernen
    //    Mitglieder entfernen, hinzufuegen muss chronologisch erfolgen
    private HashSet<Mitglied> mitglieder;
    private HashSet<Mitglied> ersatzMitglieder;
    private TreeSet<SnapShot> snapShots;

    
    
    //VB: Keine bei leeren Konstruktor
    //IV: Keine bei leeren Konstruktor
    //NB: Alle Variablen muessen instanziert sein
    public Mitglieder() {
        mitglieder = new HashSet<Mitglied>();
        ersatzMitglieder = new HashSet<Mitglied>();
        snapShots = new TreeSet<SnapShot>();
    }
    
    
    
    //VB: mitglieder && date != NULL
    //IV: Keine
    //NB: Alle Variablen muessen mit den gueltigen Werten instanziert sein
    public Mitglieder(HashSet<Mitglied> mitglieder, GregorianCalendar date) {
        this.mitglieder = mitglieder;
        this.ersatzMitglieder = new HashSet<Mitglied>();
        this.snapShots = new TreeSet<SnapShot>();
        makeSnapShot(date);
    }

    
    
    //VB: m && date != NULL
    //    m soll nicht bereits in mitglieder sein
    //    date soll chronologisch nach dem letzten Aenderungsdatum folgen
    //IV: mitglieder duerfen hier niemals weniger werden
    //NB: m soll in mitglieder eingefuegt worden sein
    //    date soll der SnapShot-Methode zur Archivierung uebergeben worden sein
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar date) {
        if (m != null && date != null) {
            boolean ok = mitglieder.add(m);
            if (ok) {
                makeSnapShot(date);
            }
            return ok;

        } else {
            return false;
        }
    }
    
    
    
    //VB: m && date != NULL
    //    m soll in mitglieder sein
    //    date soll chronologisch nach dem letzten Aenderungsdatum folgen
    //IV: mitglieder duerfen hier niemals mehr werden
    //NB: m soll aus mitglieder entfert worden sein
    //    date soll der SnapShot-Methode zur Archivierung uebergeben worden sein
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar date) {
        if (m != null && date != null) {
            boolean ok = mitglieder.remove(m);
            if (ok) {
                makeSnapShot(date);
            }
            return ok;
        } else {
            return false;
        }
    }


    
    //VB: m != NULL
    //IV: mitglieder duerfen sich nicht aendern, nur ersatzMitglieder
    //NB: m soll erfolgreich in ersatzMitglieder eingefuegt worden sein
    public boolean ersatzMitgliedHinzufuegen(Mitglied m) {
        if (m != null) {
            return ersatzMitglieder.add(m);
        } else {
            return false;
        }
    }
    
    
    
    //VB: m != NULL
    //IV: mitglieder duerfen sich nicht aendern, nur ersatzMitglieder
    //NB: m soll erfolgreich in ersatzMitglieder eingefuegt worden sein 
    public boolean ersatzMitgliedEntfernen(Mitglied m) {
        if (m != null) {
            return ersatzMitglieder.remove(m);
        } else {
            return false;
        }
    }
    
    
    
    //VB: mAusErsatz && mAusFix && date != NULL
    //    mAusErsatz und mAusFix sollen korrekt uebergeben werden
    //    date soll chronologisch nach dem letzten Aenderungsdatum folgen
    //IV: mitglieder und ersatzMitglieder muessen nach dem austauschen-
    //    gleich gross wie vor dem austauschen sein
    //NB: Die Mitglieder muessen sinngemaess ausgetauscht worden sein
    //    date soll der SnapShot-Methode zur Archivierung uebergeben worden sein
    public boolean swapMitglied(Mitglied mAusErsatz, Mitglied mAusFix, GregorianCalendar date) {
        if (mAusErsatz != null && mAusFix != null && date != null) {
            if (mitglieder.contains(mAusFix)) {
                mitglieder.remove(mAusFix);
                ersatzMitglieder.add(mAusFix);
                mitglieder.add(mAusErsatz);
                makeSnapShot(date);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    
    
    //VB: date soll chronologisch nach dem letzten Aenderungsdatum folgen
    //    Es soll zum Datum tatsaechlich eine Aenderung an mitglieder erfolgt-
    //    worden sein
    //IV: mitglieder duerfen nicht veraendert werden
    //NB: mitglieder sollen mit date in einem sinnvollen SnapShot verbunden und-
    //    in snapShots zur Archivierung eingefuegt worden sein.
    //    mitglieder duerfen nicht veraendert worden sein
    private void makeSnapShot(GregorianCalendar date) {
        if (date != null) {
            SnapShot s = new SnapShot(mitglieder, date);
            snapShots.add(s);        
        }
    }

    
    
    //VB: Es sollen mitglieder zum uebergeben vorhanden sein
    //IV: mitglieder duerfen sich nicht veraendern
    //NB: mitglieder duerfen sich nicht veraendert haben
    public HashSet<Mitglied> mitgliederAuflisten() {
        return new HashSet<Mitglied>(mitglieder);
    }
    
  
    
    //VB: date != NULL
    //    date soll im Zeitraum der mitglieder-Existenz liegen
    //IV: mitglieder und snapShots duerfen nicht veraendert werden
    //NB: Rueckgabe einer Kopie von mitglieder zum Datum date
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        if (date != null && !snapShots.isEmpty()) {
            SnapShot sTmp = snapShots.pollFirst();

            if (sTmp.getDate().after(date)) {
                return null;
            }

            for (SnapShot s : snapShots) {
                if (sTmp.getDate().before(date) && s.getDate().after(date)) {
                    return new HashSet<Mitglied>(sTmp.getMitglieder());
                }
                sTmp = s;
            }

            if (sTmp.getDate().before(date)) {
                return new HashSet<Mitglied>(mitglieder);
            }
            return null;
        } else {
            return null;
        }
    }
}
