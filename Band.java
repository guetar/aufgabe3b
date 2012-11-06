import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TreeSet;

public class Band {

    private Mitglieder mitglieder;
    private Kalender kalender;
    private Bilanz bilanz;
    private Abstimmung abstimmung;

    public Band() {
        this.mitglieder = new Mitglieder();
        this.kalender = new Kalender();
        this.bilanz = new Bilanz();
    }

    //Vorbdingung: Mitglied != null, eintrittsdatum != null
    //Nachbedingung: return:
    //true, falls das Mitglied hinzugefuegt werden und der Zustand der Band gespeichert werden konnte,
    //false andernfalls.
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar eintrittsDatum) {
        return mitglieder.mitgliedHinzufuegen(m, eintrittsDatum);
    }

    //Vorbdingung: Mitglied != null
    //Nachbedingung: return:
    //true, falls das Mitglied hinzugefuegt werden konnte,
    //false andernfalls.
    public boolean ersatzMitgliedHinzufuegen(Mitglied m) {
        return mitglieder.ersatzMitgliedHinzufuegen(m);
    }
    
    //Vorbdingung: Mitglied != null, aenderungsdatum != null
    //GUT: Alles ausgelagert an Klasse Mitglieder
    public void swapMitglied(Mitglied mAusErsatz, Mitglied mAusFix, GregorianCalendar aenderungsDatum) {
        mitglieder.swapMitglied(mAusErsatz, mAusFix, aenderungsDatum);
    }

    //Vorbdingung: Mitglied != null, austrittsdatum != null
    //achbedingung: erfolgreich entfernt
    
    //GOOD: ausgelagert an Klasse Mitglieder, Weitergabe des return-Werts der Methode
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar austrittsdatum) {
        return mitglieder.mitgliedEntfernen(m, austrittsdatum);
    }
    
    //GOOD: Es wird eine Kopie des Mitglied-Sets zurueckgegeben.
    public HashSet<Mitglied> mitgliederAuflisten() {
        return new HashSet<Mitglied>(mitglieder.mitgliederAuflisten());
    }
    
    //Vorbedingung: Datum soll in den Zeitraum der Band fallen.
    //Nachbdingung: zurueckgegebene Kopie des Sets.
    
    //GOOD: Es wird eine Kopie des Sets zurueckgegeben.
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        return new HashSet<Mitglied>(mitglieder.mitgliederAuflisten(date));
    }

    //Nachbedingung: return:
    //Liste beinhaltet Versionen wie spezifiziert,
    //s.getVon() > datum
    public ArrayList<Song> songsAuflisten(GregorianCalendar datum, boolean versionen) {
        ArrayList<Song> repertoireListe = new ArrayList<Song>();
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten(datum);
        
        for (Mitglied m : mitgliederListe) {
            if(!versionen) {
                repertoireListe.addAll(m.getRepertoire(datum));
            } else {
                for(Song s : m.getRepertoire(datum)) {
                    repertoireListe.addAll(s.getVersionen());
                }
            }
        }

        return repertoireListe;
    }
    
    //Vorbedingung Termin != null
    //Nachbedingung: return:
    //true, falls der Termin und dessen Ort dem Kalender
    //und der Bilanz der zugehoerige Posten hinzugefuegt werden konnte,
    //false, andernfalls.
    public boolean terminHinzufuegen(Termin t) {
        return  kalender.terminHinzufuegen(t) && kalender.ortHinzufuegen(t.getOrt()) && bilanz.postenHinzufuegen(t);
    }
    
    //Vorbedingung: Termin != null
    //Nachbedingung: Abstimmung aktualisiert
    public void abstimmungErzeugen(Termin t) {
        abstimmung = new Abstimmung(mitglieder.mitgliederAuflisten(), t);
    }
    
    //Vorbedingung: Termin, Mitglied und begruendung != null
    //Nachbedingung: return Erfolg
    public boolean abstimmen(Termin t, Mitglied m, boolean dafuer, String begruendung) {
        if (t == abstimmung.getTermin()) {
            return abstimmung.abstimmen(m, dafuer, begruendung);
        } else {
            return false;
        }
    }
    
    //Nachbedingung: return: Message
    public String abstimmungsErgebnis() {
        if (abstimmung.getResult()) {
            terminHinzufuegen(abstimmung.getTermin());
        } 
        return abstimmung.getResultMessage();
    }
    
    
    //Vorbedingung: Termine != null
    //Nachbedingung: return:
    //true, falls Termin, zugehoeriger Ort und Posten geaendert werden konnten
    //false andernfalls.
    
    //ERROR: return nicht richtig gesetzt.
    
    //GOOD: Durch dynamisches Binden muessen nur Termine geaendert werden.
    //BAD: Bei jeder Aenderungsaktion muessen saemtliche verknuepften Objekte einzeln geaendert werden.
    public boolean terminAendern(Termin alt, Termin neu) {
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten();
        
        Termin t = kalender.terminAendern(alt.getDatum(), neu);
        
        if(t != null) {
            
            kalender.ortLoeschen(alt.getOrt());
            kalender.ortHinzufuegen(neu.getOrt());
            if(bilanz.postenExistiert(alt)) bilanz.postenAendern(alt, neu);
            
            for(Mitglied m : mitgliederListe) {
                m.message("Folgender Termin wurde geaendert: " + t.toString());
                m.terminAendern(alt, neu);
            }
            return true;
        }
        return false;
    }
    
    //Vorbedingung: Termin != null
    //Nachbedingung: return:
    //true, falls Termin, zugehoeriger Ort und Posten geloescht werden konnten
    //false andernfalls.
    
    //ERROR: return nicht richtig gesetzt.
    
    //GOOD: Durch dynamisches Binden muessen nur Termine geloescht werden.
    //BAD: Bei jeder Loeschaktion muessen saemtliche verknuepften Objekte einzeln geloescht werden.
    public boolean terminLoeschen(Termin t) {
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten();
        
        if(kalender.terminLoeschen(t)) {
            
            kalender.ortLoeschen(t.getOrt());
            if (bilanz.postenExistiert(t)) bilanz.postenLoeschen(t);
            
            for(Mitglied m : mitgliederListe) {
                m.message("Folgender Termin wurde abgesagt: " + t.toString());
                m.terminLoeschen(t);
            }
            return true;
        }
        return false;
    }
    
    //Vorbedingung: Termin != null
    //Nachbedingung: return
    //true, falls Termin, zugehoeriger Ort und Posten wiederhergestellt werden konnten
    //alse andernfalls.
    
    //ERROR: Ort wird nicht wiederhergestellt.
    //ERROR: return nicht richtig gesetzt.
    
    //GOOD: Durch dynamisches Binden muessen nur Termine wiederhergestellt werden.
    //BAD: Bei jeder Wiederherstellungsaktion muessen saemtliche verknuepften Objekte einzeln wiederhergestellt werden.
    public Termin terminWiederherstellen(Termin t) {
        if(bilanz.postenExistiert(t)) bilanz.postenWiederherstellen(t);
        return kalender.terminWiederherstellen(t.getDatum());
    }
    
    //Vorbedingung: von < bis
    //Nachbedingung: return: Termine liegen innerhalb des gegebenen Intervalls.
    public ArrayList<? extends Termin> termineAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.termineAuflisten(von, bis);
    }
    
    //Vorbedingung: von < bis
    //Nachbedingung: return: Proben liegen innerhalb des gegebenen Intervalls.
    public ArrayList<Probe> probenAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.probenAuflisten(von, bis);
    }
    
    //Vorbedingung: von < bis
    //Nachbedingung: Auftritte liegen innerhalb des gegebenen Intervalls.
    public ArrayList<Auftritt> auftritteAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.auftritteAuflisten(von, bis);
    }
    
    //Nachbedingung: return Erfolg
    public boolean postenHinzufuegen(Posten p) {
        return bilanz.postenHinzufuegen(p);
    }
    
    //Nachbedingung: return Erfolg
    public Posten postenAendern(Posten alt, Posten neu) {
        return bilanz.postenAendern(alt, neu);
    }
    
    //Nachbedingung: return Erfolg
    public boolean postenLoeschen(Posten p) {
        return bilanz.postenLoeschen(p);
    }
    
    //Nachbedingung: return Erfolg
    public Posten postenWiederherstellen(Posten p) {
        return bilanz.postenWiederherstellen(p);
    }

    //Vorbedingung: von, bis != null
    //Nachbedingung: return: Nach Datum sortierte  Liste an Posten
    public TreeSet<Posten> postenAuflisten(boolean showAuftr, boolean showProben, boolean showEinnahmen, boolean showAusgaben, GregorianCalendar von, GregorianCalendar bis) {
        return bilanz.postenAuflisten(showAuftr, showProben, showEinnahmen, showAusgaben, von, bis);
    }

    //Vorbedingung: von, bis != null
    //Nachbedingung: return: Summe der gewuenschten Posten im gewuenschten Zeitraum
    public int postenSummieren(boolean showAuftr, boolean showProben, boolean showSonstigeEinnahmen, boolean showSonstigeAusgaben, GregorianCalendar von, GregorianCalendar bis) {
        return bilanz.postenSummieren(showAuftr, showProben, showSonstigeEinnahmen, showSonstigeAusgaben, von, bis);
    }

    //Nachbedingung: return: o.getPlaetze() >= plaetze
    public ArrayList<Ort> findeOrt(int plaetze) {
        return kalender.findeOrt(plaetze);
    }
    
    public String printMitglieder() {
        String s = "";
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten();
        
        for(Mitglied m : mitgliederListe) {
            s += m + "\n";
        }
        return s;
    }
}
