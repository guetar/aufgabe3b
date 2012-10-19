/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 *
 * @author guetar
 */
public class Band {

    private ArrayList<Mitglied> mitglieder;
    private ArrayList<Song> repertoire;
    private ArrayList<Termin> termine;
    private ArrayList<Termin> trash;

    /**
     * Konstruktor
     */
    public Band() {
        mitglieder = new ArrayList<Mitglied>();
        repertoire = new ArrayList<Song>();
        termine = new ArrayList<Termin>();
    }

    // Mitglieder
    
    /**
     * Fügt der Band ein Mitglied hinzu.
     * @param _m hinzuzufuegendes Mitglied
     */
    public void mitglied_hinzufuegen(Mitglied _m) {
        mitglieder.add(_m);
    }

    /**
     * 
     * @param _m zu entferndenes Mitglied
     */
    public void mitglied_entfernen(Mitglied _m) {
        if (mitglieder.contains(_m)) {
            mitglieder.remove(_m);
        }
    }

    /**
     * 
     * @return Mitglieder
     */
    public ArrayList<Mitglied> mitglieder_auflisten() {
        return mitglieder;
    }

    /**
     * 
     * @param _von Beginn der gesuchten Zeitspanne
     * @param _bis Ende der gesuchten Zeitspanne
     * @return 
     */
    public ArrayList<Mitglied> mitglieder_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Mitglied> mitglieder_liste = new ArrayList<Mitglied>();

        for (Mitglied m : mitglieder) {
            if (m.getVon().before(_bis) && m.getBis().after(_von)) {
                mitglieder_liste.add(m);
            }
        }

        return mitglieder_liste;
    }

    // Repertoire
    
    /**
     * 
     * @param _s hinzuzufuegender Song
     */
    public void song_hinzufuegen(Song _s) {
        repertoire.add(_s);
    }

    /**
     * 
     * @param _s zu entfernender Song
     */
    public void song_entfernen(Song _s) {
        if (repertoire.contains(_s)) {
            repertoire.remove(_s);
        }
    }

    /**
     * 
     * @return Repertoire
     */
    public ArrayList<Song> songs_auflisten() {
        return repertoire;
    }

    /**
     * 
     * @param _von gesuchter Zeitpunkt
     * @return Repertoire ab gesuchtem Zeitpunkt
     */
    public ArrayList<Song> songs_auflisten(GregorianCalendar _von) {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();

        for (Song m : repertoire) {
            if (_von.before(m.getVon())) {
                repertoire_liste.add(m);
            }
        }

        return repertoire_liste;
    }

    // Termine
    
    /**
     * 
     * @param _t hinzuzufuegender Termin
     */
    public void termin_hinzufuegen(Termin _t) {
        for (int i = 0; i < termine.size(); i++) {
            if (_t.getVon().before(termine.get(i).getVon())) {
                termine.add(i, _t);
                return;
            }
        }
        termine.add(_t);
    }
    
    /**
     * 
     * @param _alt
     * @param _neu 
     */
    public void termin_aendern(Termin _alt, Termin _neu) {
        if(termine.contains(_alt)) {
            
        }
    }
    
    /**
     * Löscht einen Termin
     *
     * @param t die gelöschten Termine
     */
    public void termin_loeschen(Termin t) {
        if(termine.contains(t)) {
            termine.remove(t);
        }
        if(!trash.contains(t)) {
            trash.add(t);
        }
    }
    
    /**
     * 
     * @return trash die Elemente des Mistkübels 
     */
    public ArrayList<Termin> trash_auflisten() {
        return trash;
    }

    public ArrayList<? extends Termin> termine_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Termin> termine_liste = new ArrayList<Termin>();

        for (Termin t : termine) {
            if (_von.before(t.getVon()) && _bis.after(t.getBis())) {
                termine_liste.add( t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return termine_liste;
    }

    public ArrayList<Probe> proben_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Probe> proben_liste = new ArrayList<Probe>();

        for (Termin t : termine) {
            if (t instanceof Probe && _von.before(t.getVon()) && _bis.after(t.getBis())) {
                proben_liste.add((Probe) t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return proben_liste;
    }

    public ArrayList<Auftritt> auftritte_auflisten(GregorianCalendar _von, GregorianCalendar _bis) {
        ArrayList<Auftritt> auftritte_liste = new ArrayList<Auftritt>();

        for (Termin t : termine) {
            if (t instanceof Auftritt && _von.before(t.getVon()) && _bis.after(t.getBis())) {
                auftritte_liste.add((Auftritt) t);
            } else if (_bis.before(t.getVon())) {
                break;
            }
        }

        return auftritte_liste;
    }

    public int kosten_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        int kosten = 0;
        ArrayList<Probe> proben_kosten = proben_auflisten(_von, _bis);

        for (Probe p : proben_kosten) {
            kosten += p.getMiete();
        }

        return kosten;
    }

    public int umsatz_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        int umsatz = 0;
        ArrayList<Auftritt> auftritte_kosten = auftritte_auflisten(_von, _bis);

        for (Auftritt a : auftritte_kosten) {
            umsatz += a.getGage();
        }

        return umsatz;
    }

    public int gewinn_summieren(GregorianCalendar _von, GregorianCalendar _bis) {
        return umsatz_summieren(_von, _bis) - kosten_summieren(_von, _bis);
    }

    /**
     * liefert Liste mit Orten, die eine bestimmte Infrastruktur haben.
     *
     * @param plaetze Gesuchte Anzahl an Zuschauerplaetzen(oder 0, wenn egal)
     * 
     * @return die Orte, die die bestimmte Infrastruktur haben. null, wenn kein
     * Ort die Voraussetzungen erfuellt.
     */
    public ArrayList<Ort> finde_ort(int _plaetze) {
        ArrayList<Ort> gef_orte = new ArrayList<Ort>();
        
		for (Termin t : termine) {
			Ort o = t.getOrt();
			if (o.getPlaetze() >= _plaetze) {
				gef_orte.add(o);
			}
		}

        return gef_orte;
    }
}
