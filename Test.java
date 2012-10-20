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
        Mitglied andreas = new Mitglied("Andreas Kodolsky", "0676/7787986", "Gitarre", new GregorianCalendar(2000, 2, 24), new GregorianCalendar(2010, 2, 24));
        Mitglied michael = new Mitglied("Michael Preis", "0664/8798653", "Bass", new GregorianCalendar(2004, 4, 6), new GregorianCalendar(2006, 4, 6));
        Mitglied lukas = new Mitglied("Lukas Permanschlager", "0676/4382904839", "Schlagzeug", new GregorianCalendar(2000, 2, 10), new GregorianCalendar(2010, 8, 12));
        Mitglied dominik = new Mitglied("Dominik Haltauf", "0664/473892347", "Bass", new GregorianCalendar(2006, 4, 6), new GregorianCalendar(2008, 4, 6));
        
        b.mitglied_hinzufuegen(andreas);
        b.mitglied_hinzufuegen(michael);
        b.mitglied_hinzufuegen(lukas);
        b.mitglied_hinzufuegen(dominik);
        
        ArrayList<Mitglied> mitglieder = b.mitglieder_auflisten(von, bis);

        System.out.println("Auflistung der Gruppenmitglieder:");
        for (Mitglied m : mitglieder) {
            System.out.println(m.toString());
        }
        System.out.println("");

        /**
         * Dem Repertoire der Band einige Songs hinzufuegen und und auflisten
         */
        Song love = new Song("I love you", "04:33", new GregorianCalendar(2012, 2, 2));
        Song nolove = new Song("I loved you but now I love another woman", "03:22", new GregorianCalendar(2012, 2, 4));
        
        andreas.addSong(love);
        andreas.addSong(nolove);
        
        Song herzschmerz = new Song("Mein Herz tut mir so weh!", "03:55", new GregorianCalendar(2012, 4, 4));
        Song beileid = new Song("Mein Beieid!", "05:20", new GregorianCalendar(2012, 6, 6));
        
        michael.addSong(herzschmerz);
        michael.addSong(beileid);
        
        ArrayList<Song> repertoire = b.songs_auflisten(new GregorianCalendar(2004, 8, 8));

        System.out.println("Auflistung des Repertoires:");
        for (Song s : repertoire) {
            System.out.println(s.toString());
        }
        System.out.println("");

        /**
         * Einige Termine hinzufuegen und auflisten
         */
        Probe p1 = new Probe(new Ort("p1 Studio", "Musterstr. 23", 8), new GregorianCalendar(1995, 6, 5, 18, 0), "3:00", 30);
        Probe p2 = new Probe(new Ort("p2 Garage", "Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 2, 18, 0), "3:00", 100);
        Probe p3 = new Probe(new Ort("p3 Keller", "Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 4, 18, 0), "3:00", 100);
        Probe p4 = new Probe(new Ort("p4 Standort", "Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 6, 18, 0), "3:00", 100);
        Probe p5 = new Probe(new Ort("p5 Keller", "Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 8, 18, 0), "3:00", 100);
        Probe p6 = new Probe(new Ort("p6 Standort", "Maxerstr. 32", 4), new GregorianCalendar(1995, 7, 10, 18, 0), "3:00", 100);
        Auftritt a1 = new Auftritt(new Ort("a1 Gasometer", "Gasstr.666", 3000), new GregorianCalendar(1995, 9, 2, 18, 0), "2:00", 800);
        Auftritt a2 = new Auftritt(new Ort("a2 Gasometer", "Gasstr.666", 3000), new GregorianCalendar(1995, 9, 3, 18, 0), "2:00", 800);
        
        b.termin_hinzufuegen(p1);
        b.termin_hinzufuegen(p2);
        b.termin_hinzufuegen(p3);
        b.termin_hinzufuegen(p4);
        b.termin_hinzufuegen(a1);
        b.termin_hinzufuegen(a2);

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
        for (Auftritt a : auftritte) {
            System.out.println(a.toString());
        }
        System.out.println("");
        
        /**
         * Termin loeschen und anschließend wiederherstellen
         */
        b.termin_loeschen(p1);
        b.termin_aendern(p3, p5);
        b.termin_aendern(p4, p6);
        
        termine = b.termine_auflisten(von, bis);

        System.out.println("Auflistung der Termine nach dem Loeschen von p1,");
        System.out.println("sowie dem Aendern von p3 in p5 bzw p4 in p6:");
        for (Termin t : termine) {
            System.out.println(t.toString());
        }
        System.out.println("");
        
        b.termin_wiederherstellen(p1);
        b.termin_wiederherstellen(p3);
        b.termin_wiederherstellen(p4);
        
        System.out.println("Dominiks Nachrichten:");
        
        ArrayList<String> messages = dominik.getMessages();
        
        for(String s : messages) {
            System.out.println(s);
        }
        System.out.println("");
        
        termine = b.termine_auflisten(von, bis);

        System.out.println("Auflistung der Termine nach der Wiederherstellung:");
        for (Termin t : termine) {
            System.out.println(t.toString());
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