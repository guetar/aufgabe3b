/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author guetar
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * Erstellen der Band und eines Zeitraums zur Suche
         */
        GregorianCalendar von = new GregorianCalendar(1985, 2, 21);
        GregorianCalendar bis = new GregorianCalendar(2012, 9, 30);
        Band b = new Band();

        /**
         * Der Band Mitglieder hinzufuegen und diese auflisten
         */
        b.mitglied_hinzufuegen(new Mitglied("Andreas Kodolsky", "0676/7787986", "Gitarre", new GregorianCalendar(2000, 2, 24), new GregorianCalendar(2010, 2, 24)));
        b.mitglied_hinzufuegen(new Mitglied("Michael Preis", "0664/8798653", "Bass", new GregorianCalendar(2004, 4, 6), new GregorianCalendar(2006, 4, 6)));
        ArrayList<Mitglied> mitglieder = b.mitglieder_auflisten(von, bis);

        System.out.println("Auflistung der Gruppenmitglieder:");
        for (Mitglied m : mitglieder) {
            System.out.println(m.toString());
        }
        System.out.println("");

        /**
         * Dem Repertoire der Band einige Songs hinzufuegen und und auflisten
         */
        b.song_hinzufuegen(new Song("I love you", "04:33", new GregorianCalendar(2012, 2, 2)));
        b.song_hinzufuegen(new Song("I loved you but now I love another woman", "03:22", new GregorianCalendar(2012, 2, 4)));
        ArrayList<Song> repertoire = b.songs_auflisten();

        System.out.println("Auflistung des Repertoires:");
        for (Song s : repertoire) {
            System.out.println(s.toString());
        }
        System.out.println("");

        /**
         * Einige Termine hinzufuegen und auflisten
         */
        b.termin_hinzufuegen(new Probe(new Ort("Studio","Musterstr. 23", 8), new GregorianCalendar(1995, 6, 5, 18, 0), "2:30", 30));
        b.termin_hinzufuegen(new Probe(new Ort("Garage","Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 2, 18, 0), "3:00", 100));
        b.termin_hinzufuegen(new Auftritt(new Ort("Stadthalle","Stadthallenstr.1",150), new GregorianCalendar(1995, 6, 5, 18, 0), "1:30", 500));
        b.termin_hinzufuegen(new Auftritt(new Ort("Gasometer","Gasstr.666",3000), new GregorianCalendar(1995, 9, 3, 18, 0), "2:00", 800));

        ArrayList<? extends Termin> termine = b.termine_auflisten(von, bis);

        System.out.println("Auflistung aller Termine:");
        for (Termin t : termine) {
            System.out.println(t.toString());
        }
        System.out.println("");

        ArrayList<Probe> proben = b.proben_auflisten(von, bis);

        System.out.println("Auflistung aller Proben:");
        for (Probe p : proben) {
            System.out.println(p.toString());
        }
        System.out.println("");

        ArrayList<Auftritt> auftritte = b.auftritte_auflisten(von, bis);

        System.out.println("Auflistung aller Auftritte:");
        for (Probe p : proben) {
            System.out.println(p.toString());
        }
        System.out.println("");

        /**
         * Eine Bilanz über den gesuchten Zeitraum erstellen
         */
        System.out.println("Gesamtkosten in diesem Zeitraum:" + b.kosten_summieren(von, bis) + " Euro");
        System.out.println("Gesamtumsatz in diesem Zeitraum:" + b.umsatz_summieren(von, bis) + " Euro");
        System.out.println("Macht einen Gesamtgewinn von:" + b.gewinn_summieren(von, bis) + " Euro");
        System.out.println("");
        
        /**
         * Einige Orte hinzufuegen und auflisten
         */
        System.out.println("Orte mit mehr als 200 Plätzen:");
        ArrayList<Ort> orte = b.finde_ort(200);
        for (Ort o : orte) {
        	System.out.println(o);
        }
        System.out.println(""); 
    }
}
