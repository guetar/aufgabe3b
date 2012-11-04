import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author guetar
 */
public class Kalender {

    // Invariante: Termine immer aufsteigend sortiert.
    private TreeSet<Termin> termine;
    // Invariante: Orte immer aufsteigend sortiert.
    private TreeSet<Ort> orte;
    private LinkedList<Termin> trash;

    public Kalender() {
        this.termine = new TreeSet<Termin>();
        this.trash = new LinkedList<Termin>();
        this.orte = new TreeSet<Ort>();
    }

    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Ort nicht vorhanden war und erfolgreich hinzugefuegt wurde,
     * falsch, falls der Ort schon vorhanden war oder nicht erfolgreich hinzugefuegt werden konnte.
     */
    public boolean ortHinzufuegen(Ort o) {
        if (!orte.contains(o)) {
            return orte.add(o);
        }
        return false;
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Wert imer
     * true, falls der Ort vorhanden war und erfolgreich geloescht werden konnte,
     * false, falls der Ort nicht vorhanden war oder nicht erfolgreich geloescht werden konnte.
     */
    public boolean ortLoeschen(Ort o) {
        if (orte.contains(o)) {
            return orte.remove(o);
        }
        return false;
    }

    /**
     * Nachgedingung
     * 
     * Retournierte Orte besitzen immer
     * Plaetze >= gesuchte Plaetze.
     */
    public ArrayList<Ort> findeOrt(int plaetze) {
        ArrayList<Ort> gefOrte = new ArrayList<Ort>();

        for (Ort o : orte) {

            if (o.getPlaetze() >= plaetze) {
                gefOrte.add(new Ort(o));
            }
        }

        return gefOrte;
    }

    /**
     * Nachbedingung
     * 
     * Retorunierter Wert immer
     * true, falls der Termin noch nicht vorhanden war und erfolgreich hinzugefuegt werden konnte,
     * false, falls der Termin bereits vorhanden war oder nicht erfolgreich hinzugefuegt werden konnte.
     * 
     * GOOD: Durch dynamisches Binden ist nur eine Liste zur Verwaltung von Proben und Auftritten notwendig.
     */
    public boolean terminHinzufuegen(Termin t) {
        if (!termine.contains(t)) {
            return termine.add(t);
        }
        return false;
    }

    /**
     * Vorbedingung
     * 
     * Neuer Termin darf nicht null sein, das sonst eine NullpointerException entsteht.
     * 
     * Nachbedingung
     * 
     * Retournierter Termin immer
     * Instanz von Probe, falls eine Probe geaendert wurde,
     * Instanz von Auftritt, falls ein Auftritt geaendert wurde,
     * null, falls der Termin nicht gefunden wurde.
     * 
     * BAD: Durch dynamisches Binden ist diese Funktion sehr unuebersichtlich,
     * da immer festgestellt werden muss, Instanz welcher Klasse das aktuelle Objekt ist.
     */
    public Termin terminAendern(GregorianCalendar alt, Termin neu) {
        for (Termin t: termine) {
            if (t.getDatum().equals(alt)) {
                termine.remove(t);
                Termin t_neu = t.setTermin(neu);
                termine.add(t.setTermin(neu));
                return t_neu;
            }
        }
        return null;
    }

    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Termin vorhanden war und erfolgreich geloescht werden konnte,
     * false, falls der Termin nicht vorhanden oder bereits geloescht war.
     * 
     * GOOD: Durch dynamisches Binden muss der Termin, egal ob Probe oder Auftritt,
     * nur in einer Liste gesucht werden.
     */
    public boolean terminLoeschen(Termin t) {
        if (termine.contains(t) && !trash.contains(t)) {
            trash.add(t);
            termine.remove(t);
            
            for (Mitglied m : t.getTeilnehmer()) {
                m.terminLoeschen(t);
            }
            return true;
        }
        return false;
    }

    /**
     * Invariante
     * 
     * Sicherstellen, dass Termine immer noch aufsteigend sortiert sind durch
     * .remove() und anschließendes .add().
     * 
     * Nachbedingung
     * 
     * Retournierter Termin immer
     * Instanz von Probe, falls eine Probe wiederhergestellt wurde,
     * Instanz von Auftritt, falls ein Auftritt wiederhergestellt wurde,
     * null, falls der Termin nicht gefunden wurde.
     * 
     * BAD: Durch dynamisches Binden ist diese Funktion sehr unuebersichtlich,
     * da immer festgestellt werden muss, Instanz welcher Klasse das aktuelle Objekt ist.
     */
    public Termin terminWiederherstellen(GregorianCalendar datum) {
        for (Termin t : termine) {
            if (t.getDatum().equals(datum)) {
                Termin alt = t.popFromStack();

                termine.remove(t);
                Termin t_neu = t.setTermin(alt);
                termine.add(t_neu);
                return t_neu;
            }
        }
        
        for(Termin t : trash) {
            if (t.getDatum().equals(datum)) {
                // keine alte Version vorhanden => Termin muss geloescht worden sein
                if (trash.contains(t)) {
                    trash.remove(t);
                    termine.add(t);
                    return t;
                }
            }
        }
        
        return null;
    }

    /**
     * GOOD: Durch dynamisches Binden kann leicht eine allgemeine Terminliste zurueckgegeben werden.
     */
    public TreeSet<? extends Termin> termineAuflisten() {
        TreeSet<Termin> terminListe = new TreeSet<Termin>();
        
        for (Termin t : termine) {
            if(t.getKategorie()==2) {
                terminListe.add(new Probe(t));
            }
            if(t.getKategorie()==1) {
                terminListe.add(new Auftritt(t));
            }
        }
        
        return terminListe;
    }

    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * Retournierte Termine immer
     * innerhalb des gegebenen Zeitraums.
     * von < t.getVon() && t.getBis() < bis
     * 
     * GOOD: Durch dynamisches Binden muss nur eine Liste durchiteriert werden.
     */
    public ArrayList<? extends Termin> termineAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Termin> terminListe = new ArrayList<Termin>();

        for (Termin t : termine) {
            if (von.before(t.getDatum()) && bis.after(t.getDatum())) {
                if(t.getKategorie()==2) {
                    terminListe.add(new Probe(t));
                }
                if(t.getKategorie()==1) {
                    terminListe.add(new Auftritt(t));
                }
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return terminListe;
    }

    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * Retournierte Proben immer
     * innerhalb des gegebenen Zeitraums.
     * von < t.getVon() && t.getBis() < bis
     * 
     * BAD: Durch dynamisches Binden muss bei jeder Iteration zusaetzlich ueberprueft werden,
     * ob der Termin Instanz von Probe ist.
     */
    public ArrayList<Probe> probenAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Probe> probenListe = new ArrayList<Probe>();

        for (Termin t : termine) {
            if (t.getKategorie()==2 && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                probenListe.add(new Probe(t));
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return probenListe;
    }

    /**
     * Vorbedingung
     * 
     * von chronologisch vor bis
     * 
     * Nachbedingung
     * 
     * Retournierte Auftritte immer
     * innerhalb des gegebenen Zeitraums
     * von < t.getVon() && t.getBis() < bis
     * 
     * BAD: Durch dynamisches Binden muss bei jeder Iteration zusaetzlich ueberprueft werden,
     * ob der Termin Instanz von Auftritt ist.
     */
    public ArrayList<Auftritt> auftritteAuflisten(GregorianCalendar von, GregorianCalendar bis) {
        ArrayList<Auftritt> auftritteListe = new ArrayList<Auftritt>();

        for (Termin t : termine) {
            if (t.getKategorie()==1 && von.before(t.getDatum()) && bis.after(t.getDatum())) {
                auftritteListe.add(new Auftritt(t));
            } else if (bis.before(t.getDatum())) {
                break;
            }
        }

        return auftritteListe;
    }
    
    /**
     * GOOD: Durch dynamisches Binden ist nur eine Liste zum Loeschen von Proben und Auftritten notwendig.
     */
    public LinkedList<Termin> trashAuflisten() {
        LinkedList<Termin> trashListe = new LinkedList<Termin>();
        
        for (Termin t : trash) {
            if(t.getKategorie()==2) {
                trashListe.add(new Probe(t));
            }
            if(t.getKategorie()==1) {
                trashListe.add(new Auftritt(t));
            }
        }
        
        return trashListe;
    }
}
