package com;

import java.util.*;

public class Band {

    private Mitglieder mitglieder;
    private Kalender kalender;
    private Bilanz bilanz;

    public Band() {
        this.mitglieder = new Mitglieder();
        this.kalender = new Kalender();
        this.bilanz = new Bilanz();
    }

    /**
     * Vorbedingung
     * 
     * Mitglied sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls das Mitglied hinzugefuegt werden und der Zustand der Band gespeichert werden konnte,
     * false andernfalls.
     */
    public boolean mitgliedHinzufuegen(Mitglied m, GregorianCalendar eintrittsDatum) {
        return mitglieder.mitgliedHinzufuegen(m, eintrittsDatum);
    }

    /**
     * Vorbedingung
     * 
     * Mitglied sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls das Mitglied hinzugefuegt werden konnte,
     * false andernfalls.
     */
    public boolean ersatzMitgliedHinzufuegen(Mitglied m) {
        return mitglieder.ersatzMitgliedHinzufuegen(m);
    }
    
    /**
     * Vorbedingung.
     * 
     * Mitglieder sollten nicht NULL sein.
     * Mitglieder sollten nicht vertauscht sein!
     * 
     * Nachbedingung
     * 
     * keine
     * 
     * GUT:
     * Alles ausgelagert an Klasse Mitglieder
     */
    public void swapMitglied(Mitglied mAusErsatz, Mitglied mAusFix, GregorianCalendar aenderungsDatum) {
        mitglieder.swapMitglied(mAusErsatz, mAusFix, aenderungsDatum);
    }

    /**
     * Vorbedingung.
     * 
     * Mitglied sollten nicht NULL sein,
     * Mitglied sollte in Band sein.
     * 
     * Nachbedingung
     * 
     * erfolgreich entfernt
     * 
     * GUT:
     * ausgelagert an Klasse Mitglieder, Weitergabe des return-Werts der Methode
     */
    public boolean mitgliedEntfernen(Mitglied m, GregorianCalendar austrittsdatum) {
        return mitglieder.mitgliedEntfernen(m, austrittsdatum);
    }
    
    /**
     * Vorbedingung, Nachbedingung.
     * 
     * Keine
     * 
     * GUT:
     * >Es wird eine Kopie des Mitglied-Sets zurueckgegeben.
     */
    public HashSet<Mitglied> mitgliederAuflisten() {
        return new HashSet<Mitglied>(mitglieder.mitgliederAuflisten());
    }
    
    /**
     * Vorbedingung.
     * 
     * Datum soll in den Zeitraum der Band fallen.
     * 
     * Nachbdingung.
     * 
     * zurueckgegebene Kopie des Sets.
     * 
     * GUT:
     * >Es wird eine Kopie des Sets zurueckgegeben.
     */
    public HashSet<Mitglied> mitgliederAuflisten(GregorianCalendar date) {
        return new HashSet<Mitglied>(mitglieder.mitgliederAuflisten(date));
    }

    /**
     * Nachbedingung
     * 
     * return
     * Liste beinhaltet Versionen wie spezifiziert.
     * Songs sind alle nach dem spezifizierten Datum entstanden.
     * s.getVon() > datum
     */
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
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Termin sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls der Termin und dessen Ort dem Kalender
     * und der Bilanz der zugehoerige Posten hinzugefuegt werden konnt,
     * false, andernfalls.
     */
    public boolean terminHinzufuegen(Termin t) {
        return  kalender.terminHinzufuegen(t) && kalender.ortHinzufuegen(t.getOrt()) && bilanz.postenHinzufuegen(new Posten(t));
    }
    
    /**
     * Erzeugt eine neue Abstimmung zu einem Termin
     * 
     * @param t abzustimmender Termin
     * @return Abstimmung
     */
    public Abstimmung abstimmenTermin(Termin t) {
        return new Abstimmung(mitglieder.mitgliederAuflisten(), t);
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebene Termine sollten nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls Termin, zugehoeriger Ort und Posten geaendert werden konnten
     * false andernfalls.
     * ERROR: return nicht richtig gesetzt.
     * 
     * GOOD: Durch dynamisches Binden muessen nur Termine geaendert werden.
     * BAD: Bei jeder Aenderungsaktion muessen saemtliche verknuepften Objekte einzeln geaendert werden.
     */
    public boolean terminAendern(Termin alt, Termin neu) {
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten();
        
        Termin t = kalender.terminAendern(alt.getDatum(), neu);
        
        if(t != null) {
            
            Posten p = new Posten(alt);
            kalender.ortLoeschen(alt.getOrt());
            kalender.ortHinzufuegen(neu.getOrt());
            if(bilanz.postenExistiert(p)) bilanz.postenAendern(p, new Posten(neu));
            
            for(Mitglied m : mitgliederListe) {
                m.message("Folgender Termin wurde geaendert: " + t.toString());
                m.terminAendern(alt, neu);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Termin sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls Termin, zugehoeriger Ort und Posten geloescht werden konnten
     * false andernfalls.
     * ERROR: return nicht richtig gesetzt.
     * 
     * GOOD: Durch dynamisches Binden muessen nur Termine geloescht werden.
     * BAD: Bei jeder Loeschaktion muessen saemtliche verknuepften Objekte einzeln geloescht werden.
     */
    public boolean terminLoeschen(Termin t) {
        HashSet<Mitglied> mitgliederListe = this.mitglieder.mitgliederAuflisten();
        
        if(kalender.terminLoeschen(t)) {
            
            Posten p = new Posten(t);
            kalender.ortLoeschen(t.getOrt());
            if (bilanz.postenExistiert(p)) bilanz.postenLoeschen(p);
            
            for(Mitglied m : mitgliederListe) {
                m.message("Folgender Termin wurde abgesagt: " + t.toString());
                m.terminLoeschen(t);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Vorbedingung
     * 
     * Termin sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * return
     * true, falls Termin, zugehoeriger Ort und Posten wiederhergestellt werden konnten
     * false andernfalls.
     * ERROR: Ort wird nicht wiederhergestellt.
     * ERROR: return nicht richtig gesetzt.
     * 
     * GOOD: Durch dynamisches Binden muessen nur Termine wiederhergestellt werden.
     * BAD: Bei jeder Wiederherstellungsaktion muessen saemtliche verknuepften Objekte einzeln wiederhergestellt werden.
     */
    public Termin terminWiederherstellen(Termin t) {
        Posten p = new Posten(t);
        if(bilanz.postenExistiert(p)) bilanz.postenWiederherstellen(p);
        return kalender.terminWiederherstellen(t.getDatum());
    }
    
    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * return
     * Termine liegen innerhalb des gegebenen Intervalls.
     */
    public ArrayList<? extends Termin> termineAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.termineAuflisten(von, bis);
    }
    
    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * return
     * Proben liegen innerhalb des gegebenen Intervalls.
     */
    public ArrayList<Probe> probenAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.probenAuflisten(von, bis);
    }
    
    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * return
     * Auftritte liegen innerhalb des gegebenen Intervalls.
     */
    public ArrayList<Auftritt> auftritteAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        return kalender.auftritteAuflisten(von, bis);
    }
    
    /**
     * Nachbedingung
     * 
     * return
     * true, falls der Posten erfolgreich hinzugefuegt werden konnte,
     * false andernfalls.
     */
    public boolean postenHinzufuegen(Posten p) {
        return bilanz.postenHinzufuegen(p);
    }
    
    /**
     * Nachbedingung
     * 
     * return
     * true, falls der Posten erfolgreich geaendert werden konnte,
     * false andernfalls.
     */
    public Posten postenAendern(Posten alt, Posten neu) {
        return bilanz.postenAendern(alt, neu);
    }
    
    /**
     * Nachbedingung
     * 
     * return
     * true, falls der Posten erfolgreich geloescht werden konnte,
     * false andernfalls.
     */
    public boolean postenLoeschen(Posten p) {
        return bilanz.postenLoeschen(p);
    }
    
    /**
     * Nachbedingung
     * 
     * return
     * true, falls der Posten erfolgreich wiederhergestellt werden konnte,
     * false andernfalls.
     */
    public Posten postenWiederherstellen(Posten p) {
        return bilanz.postenWiederherstellen(p);
    }

    /**
     * Vorbedingung
     * 
     * von!=null, bis!=null
     * 
     * Nachbedingung
     * 
     * return
     * Nach Datum sortierte  Liste an Posten
     */
    public TreeSet<Posten> postenAuflisten(boolean showAuftr, boolean showProben, boolean showEinnahmen, boolean showAusgaben, GregorianCalendar von, GregorianCalendar bis) {
        return bilanz.postenAuflisten(showAuftr, showProben, showEinnahmen, showAusgaben, von, bis);
    }

    /**
     * Vorbedingung
     * 
     * von!=null, bis!=null
     * 
     * Nachbedingung
     * 
     * return
     * Summe der gewuenschten Posten im gewuenschten Zeitraum 
     */
    public int postenSummieren(boolean showAuftr, boolean showProben, boolean showSonstigeEinnahmen, boolean showSonstigeAusgaben, GregorianCalendar von, GregorianCalendar bis) {
        return bilanz.postenSummieren(showAuftr, showProben, showSonstigeEinnahmen, showSonstigeAusgaben, von, bis);
    }

    /**
     * Nachbedingung
     * 
     * return
     * Orte mit gleich vielen oder mehr Plaetzen als gesucht.
     * o.getPlaetze() >= plaetze
     */
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
