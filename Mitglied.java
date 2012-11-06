import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Mitglied {

    
    
    //IV: zugestellte Nachrichten muessen erhalten bleiben
    private String name;
    private String tel;
    private String instrument;
    private ArrayList<Song> repertoire;
    private ArrayList<String> messages;
    private ArrayList<Termin> termine;

    
    
    //VB: name && tel && != NULL
    //IV: duerfen sich nicht mehr aendern
    //NB: Objekt erfolgreich instanziert
    public Mitglied(String name, String tel, String instrument) {
        this.name = name;
        this.tel = tel;
        this.instrument = instrument;
        this.repertoire = new ArrayList<Song>();
        this.messages = new ArrayList<String>();
        this.termine = new ArrayList<Termin>();
    }
    
    
    
    //VB: m != NULL
    //IV: m darf sich nicht aendern
    //NB: m muss hinzugefuegt worden sein
    public Boolean message(String m) {
        if (m != null) {
            return messages.add(m);
        }
        else {
            return false;
        }
    }
    
    
    
    //VB: messages != NULL
    //IV: messages darf sich nicht aendern
    //NB: messages als Kopie erfolgreich zurueckgegeben
    public ArrayList<String> getMessages() {
        return new ArrayList<String>(messages);
    }
    
  
    
    //VB: s != NULL
    //IV: repertoire darf nicht weniger werden
    //NB: s erfolgreich zu repertoire hinzugefuegt
    public Boolean songHinzufuegen(Song s) {
        if (s != null) {
            return repertoire.add(s);
        }
        else {
            return false;
        }
    }
    
    

    //VB: date != NULL
    //IV: repertoire darf nicht geandert werden
    //NB: Rueckgabe des repertoires in From neuer Liste
    public ArrayList<Song> getRepertoire(GregorianCalendar date) {
        if(date != null) {
            ArrayList<Song> repertoireListe = new ArrayList<Song>();

            for (Song s : repertoire) {
                if (s.getVon().before(date)) {
                    repertoireListe.add(s);
                }
            }
            return repertoireListe;
        }
        else {
            return null;
        }
    }
    
    

    //VB: t != NULL
    //IV: termine darf nicht weniger werden
    //NB: t soll in termine eingefuegt worden sein
    public boolean addTermin(Termin t) {
        if (t != null) {
            return termine.add(t);
        }
        else {
            return false;
        }
    }
    
    
    
    //VB: t != NULL
    //IV: termine darf nicht mehr werden
    //NB: t soll aus termine geloescht worden sein
    public boolean terminLoeschen(Termin t) {
        if (t != null) {
            for (Termin tList : termine) {
                if (tList.getDatum().equals(t.getDatum()) && tList.getOrt().getName().equals(t.getOrt().getName())) {
                    termine.remove(tList);
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    
    
    //VB: name != NULL
    //IV: name darf sich nicht aendern
    //NB: name erfolgreich zurueckgegeben
    public String getName() {
        return name;
    }

    
    
    //VB: name && tel && instrument != NULL
    //IV: name && tel && instrument duerfen sich nicht aendern
    //NB: name && tel && instrument als String zurueckgegeben
    public String toString() {
        return name + " " + tel + " " + instrument;
    }

 
    
    //VB: termine != NULL
    //IV: termine duerfen sich nicht aendern
    //NB: Kopie von termine zurueckgegeben
    public ArrayList<Termin> getTermine() {
        return new ArrayList<Termin>(termine);
    }

  
    
    //VB: alt && neu != NULL
    //    alt soll vorhanden sein
    //IV: Bis auf den Termin alt darf sich kein anderer aendern
    //NB: alt soll erfolgreich durch neu ausgetauscht worden sein
    public boolean terminAendern(Termin alt, Termin neu) {
        if (alt != null && neu != null) {
            boolean ok = termine.remove(alt);
            if (ok) {
                termine.add(neu);
            }
            return ok;
        }
        else {
            return false;
        }
    }
}
