
package com;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Mitglied {

    
    /**
     * KLASSE Mitglied.
     * 
     * 
     * INVARIANTE:
     * >einmal zugestellte Nachrichten sollen erhalten bleiben, alles
     * andere sollte aenderbar sein. (Ist es aber momentan nicht)
     * 
     * GUT:
     * >Mitglied ist eine simple Klasse mit wenig Eigenschaften und einfachen
     * Methoden.
     * 
     * SCHLECHT:
     * >Eigenschaften wie Name oder Telefonnummer koennen nicht geaendert werden.
     */
    private String name;
    private String tel;
    private String instrument;
    private ArrayList<Song> repertoire;
    private ArrayList<String> messages;
    private ArrayList<Termin> termine;

    
    
    
    
    /**
     * KONSTRUKTOR.
     * 
     * 
     * VORBEDINGUNG:
     * >Die eingehenden Parameter sollen sinnvolle Werte haben.
     * 
     * INVARIANTE:
     * >keine nennenswerten
     * 
     * NACHBEDINGUNG:
     * >Die Parameter sollen gespeichert sein.
     */
    public Mitglied(String name, String tel, String instrument) {
        this.name = name;
        this.tel = tel;
        this.instrument = instrument;
        this.repertoire = new ArrayList<Song>();
        this.messages = new ArrayList<String>();
        this.termine = new ArrayList<Termin>();
    }
    
    
    
    
    
    /**
     * METHODE message.
     * 
     * VB:
     * >Die Nachricht soll sinngemaess sein.
     * 
     * IV, NB:
     * >keine nennenswerten.
     */
    public Boolean message(String m) {
        return messages.add(m);
    }
    
    
    
    /**
     * METHODE getMessages.
     * 
     * 
     * VB:
     * >keine
     * 
     * IV:
     * >keine
     * 
     * NB:
     * >Die String-Liste soll zurueckgegeben werden.
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    
    
    /**
     * METHODE songHinzufuegen.
     * 
     * 
     * VB:
     * >Song sollte einer sein, der noch nicht im repertoire ist.
     * 
     * IV:
     * >keine
     * 
     * NB:
     * >Song soll dem repertoire hinzugefuegt worden sein.
     * 
     * GUT:
     * >einfache Implementierung
     * 
     * SCHLECHT:
     * >Keine Kontrolle ob Song schon vorhanden.
     */
    public Boolean songHinzufuegen(Song s) {
        return repertoire.add(s);
    }
    
    
    
    
    /**
     * METHODE getRepertoire.
     * 
     * 
     * VB:
     * >Datum sollte sinnvoll geweahlt worden sein.
     * 
     * IV:
     * >keine nennenswerte
     * 
     * NB:
     * >Liste mit Songs ausgegeben.
     * 
     * GUT:
     * >Es wird eine neue ArrayList zurueckgegeben, nicht das Original
     */
    public ArrayList<Song> getRepertoire(GregorianCalendar datum) {
        ArrayList<Song> repertoireListe = new ArrayList<Song>();
        
        for(Song s : repertoire) {
            if(s.getVon().before(datum)) {
                repertoireListe.add(s);
            }
        }
        return repertoireListe;
    }
    
    
    
    /**
     * METHODE addTermin.
     * 
     * 
     * VB:
     * >Termin soll auch wirklich einer sein, der die band betrifft.
     * 
     * IV:
     * >Keine nennenswerte
     * 
     * NB:
     * >Termin soll hinzugefuegt worden sein.
     * 
     * GUT:
     * >einfach
     * 
     * SCHLECHT:
     * >Keine Kontrolle ob Termin in band vorhanden.
     * >Datenstruktur nicht geeignet und auch nicht chronologisch sortiert.
     * 
     */
    public boolean addTermin(Termin t) {
        return termine.add(t);
    }
    
    
    
    
    /**
     * METHODE terminLoeschen.
     * 
     * 
     * VB:
     * >Termin soll vorhanden sein.
     * 
     * IV:
     * >Keine nennenswerte
     * 
     * NB:
     * >Termin soll falls vorhanden geloescht worden sein.
     * 
     * SCHLECHT:
     * >ungeeignete Datenstruktur, HashSet waere effizienter.
     */
    public boolean terminLoeschen(Termin t) {
        for (Termin tList : termine) {
            if (tList.getDatum().equals(t.getDatum()) && tList.getOrt().getName().equals(t.getOrt().getName())) {
                termine.remove(tList);
                return true;
            }
        }
        return false;
    }

    
    
    /**
     * no comment.
     */
    public String getName() {
        return name;
    }

    /**
     * no comment.
     */
    public String toString() {
        return name + " " + tel + " " + instrument;
    }

 
    /**
     * no comment.
     */
    public ArrayList<Termin> getTermine() {
        return termine;
    }

    
    /**
     * METHODE terminAendern.
     * 
     * 
     * VB:
     * >Termin sollte vorhanden sein.
     * 
     * IV:
     * >keine nennenswerte
     * 
     * NB:
     * >Termin falls vorhanden, geaendert.
     * 
     * SCHLECHT:
     * >ungeeignete Datenstruktur, HashSet waere effizienter.
     */
    public boolean terminAendern(Termin alt, Termin neu) {
        boolean ok = termine.remove(alt);
        if (ok) {
            termine.add(neu);
        }
        return ok;
    }
}
