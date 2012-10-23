/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TreeSet;

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
         * Mitglieder erzeugen
         */
        Mitglied andreas = new Mitglied("Andreas Kodolsky", "0676/7787986", "Gitarre");
        Mitglied michael = new Mitglied("Michael Preis", "0664/8798653", "Bass");
        Mitglied lukas = new Mitglied("Lukas Permanschlager", "0676/4382904839", "Schlagzeug");
        Mitglied dominik = new Mitglied("Dominik Haltauf", "0664/473892347", "Bass");
        
        Song love = new Song("Love: I love you", "04:33", new GregorianCalendar(2002, 2, 2));
        Song nolove = new Song("No Love: I loved you but now I love another woman", "03:22", new GregorianCalendar(2003, 2, 4));
        Song notso = new Song("Version von Love: I love you not so much any more", "04:33");
        Song hate = new Song("Version von No Love: I loved you now I hate you", "03:22");
        love.versionHinzufuegen(notso);
        nolove.versionHinzufuegen(hate);
        
        andreas.songHinzufuegen(love);
        andreas.songHinzufuegen(nolove);
        
        Song herzschmerz = new Song("Herz: Mein Herz tut mir so weh!", "03:55", new GregorianCalendar(2000, 8, 4));
        Song beileid = new Song("Beileid: Mein Beieid!", "05:20", new GregorianCalendar(2002, 6, 6));
        
        michael.songHinzufuegen(herzschmerz);
        michael.songHinzufuegen(beileid);
        
        Song schloger = new Song("Herzilein: Herzilein, brauchst nicht traurig sein", "04:55", new GregorianCalendar(2005, 2, 6));
        Song schlogzeg = new Song("Kraut: Kraut und Ruabn durchanond", "05:44", new GregorianCalendar(2004, 2, 8));
        
        lukas.songHinzufuegen(schloger);
        lukas.songHinzufuegen(schlogzeg);
        
        Song triest = new Song("Kuhflade: Das Leben ist eine Kuhflade und ich scheiß darauf", "06:30", new GregorianCalendar(2003, 5, 7));
        Song happy = new Song("Mut: Das Leben ist plötzlich voll gut und ich hab wieder Mut", "08:24", new GregorianCalendar(2003, 7, 5));
        
        dominik.songHinzufuegen(triest);
        dominik.songHinzufuegen(happy);
        
        /**
         * Mitglieder der band hinzufuegen, entfernen und wieder hinzfuegen
         */
        b.mitgliedHinzufuegen(andreas, new GregorianCalendar(2000, 2, 24));
        b.mitgliedHinzufuegen(michael, new GregorianCalendar(2000, 2, 24));
        b.mitgliedHinzufuegen(lukas, new GregorianCalendar(2000, 2, 24));
        b.mitgliedHinzufuegen(dominik, new GregorianCalendar(2000, 4, 6));  
        b.mitgliedEntfernen(lukas, new GregorianCalendar(2001, 3, 2));
        b.mitgliedEntfernen(dominik, new GregorianCalendar(2002, 5, 2));
        b.mitgliedHinzufuegen(lukas, new GregorianCalendar(2003, 5, 6));
        
        /**
         * Ausgabe der Mitglieder zu bestimmten Zeitpunkten
         */
        System.out.println("\nAusgabe Mitglieder----------------------------------");
        System.out.println("Erwarteter Output:\n\n2000, 3, 12: 3 Mitglieder\n2001, 2, 2 : 4 Mitglieder\n2002, 3, 2 : 3 Mitglieder\n2003, 3, 2 :  2 Mitglieder\n\nAktuell: 3 Mitglieder");
        System.out.println("----------------------------------------------------\n");
        
        System.out.println("Auflistung der Gruppenmitglieder zum Datum 2000, 3, 12:");
        HashSet<Mitglied> mitglieder = b.mitgliederAuflisten(new GregorianCalendar(2000, 3, 3));
        if (mitglieder != null) {
            for (Mitglied m : mitglieder) {
                System.out.println(m.toString());
            }
        }
        else { System.out.println("Ungueltiges Datum"); }
        
        System.out.println("\nAuflistung der Gruppenmitglieder zum Datum 2001, 2, 2 :");
        mitglieder = b.mitgliederAuflisten(new GregorianCalendar(2001, 2, 2));
        if (mitglieder != null) {
            for (Mitglied m : mitglieder) {
                System.out.println(m.toString());
            }
        }
        else { System.out.println("Ungültiges Datum"); }
        
        System.out.println("\nAuflistung der Gruppenmitglieder zum Datum 2002, 3, 2 :");
        mitglieder = b.mitgliederAuflisten(new GregorianCalendar(2002, 3, 2));
        if (mitglieder != null) {
            for (Mitglied m : mitglieder) {
                System.out.println(m.toString());
            }
        }
        else { System.out.println("Ungültiges Datum"); }
        
        System.out.println("\nAuflistung der Gruppenmitglieder zum Datum 2003, 3, 2 :");
        mitglieder = b.mitgliederAuflisten(new GregorianCalendar(2003, 3, 2));
        if (mitglieder != null) {
            for (Mitglied m : mitglieder) {
                System.out.println(m.toString());
            }
        } 
        else { System.out.println("Ungültiges Datum"); }
        
        System.out.println("\nAuflistung der aktuellen Gruppenmitglieder:");
        mitglieder = b.mitgliederAuflisten();
        if (mitglieder != null) {
            for (Mitglied m : mitglieder) {
                System.out.println(m.toString());
            }
        }
        else { System.out.println("Ungültiges Datum"); }
        System.out.println("");

        /**
         * Dem Repertoire der Band einige Songs hinzufuegen und und auflisten
         */
        
        System.out.println("\nAusgabe Songs---------------------------------------");
        System.out.println("Erwarteter Output:\n\n4 Lieder: Love, Version von Love, Herz, Beileid\n6 Lieder: Love, Version von Love, No Love, Version von No Love, Herz, Beileid, Herzilein, Kraut");
        System.out.println("----------------------------------------------------\n");
        
        ArrayList<Song> repertoire = b.songsAuflisten(new GregorianCalendar(2002, 8, 8), true);

        System.out.println("Auflistung des Repertoires zum Datum 2002, 8, 8:");
        for (Song s : repertoire) {
            System.out.println(s.toString());
        }
        System.out.println("");
        
        repertoire = b.songsAuflisten(new GregorianCalendar(2005, 6, 6), true);
        
        System.out.println("Auflistung des Repertoires zum Datum 2005, 6, 6:");
        for (Song s : repertoire) {
            System.out.println(s.toString());
        }
        System.out.println("");

        /**
         * Einige Termine hinzufuegen und auflisten
         */
        
        System.out.println("\nAusgabe Termine-------------------------------------");
        System.out.println("Erwarteter Output:\n\n4 Proben, 2 Auftritte\nNach dem Löschen und Ändern: P2, P5, A1, P6, A2\nNach dem Wiederherstellen: P2, P3, A1, P4, A2");
        System.out.println("----------------------------------------------------\n");
        
        Probe p1 = new Probe(new Ort("P1 Studio", "Musterstr. 23", 8), new GregorianCalendar(2001, 6, 5, 18, 0), "3:00", mitglieder, 30);
        Probe p2 = new Probe(new Ort("P2 Garage", "Maxerstr. 32", 4), new GregorianCalendar(2001, 7, 2, 18, 0), "3:00", mitglieder, 90);
        Probe p3 = new Probe(new Ort("P3 Keller", "Maxerstr. 32", 4), new GregorianCalendar(2001, 7, 4, 18, 0), "3:00", mitglieder, 50);
        Probe p4 = new Probe(new Ort("P4 Standort", "Maxerstr. 32", 4), new GregorianCalendar(2002, 7, 6, 18, 0), "3:00", mitglieder, 60);
        Auftritt a1 = new Auftritt(new Ort("A1 Rauschhaus", "Alkgasse 13", 50), new GregorianCalendar(2001, 9, 2, 18, 0), "2:00", mitglieder, 800);
        Auftritt a2 = new Auftritt(new Ort("A2 Gasometer", "Gasstr.666", 3000), new GregorianCalendar(2002, 9, 3, 18, 0), "2:00", mitglieder, 800);
        
        b.terminHinzufuegen(p1);
        b.terminHinzufuegen(p2);
        b.terminHinzufuegen(p3);
        b.terminHinzufuegen(p4);
        b.terminHinzufuegen(a1);
        b.terminHinzufuegen(a2);

        ArrayList<? extends Termin> termine = b.termineAuflisten(von, bis);

        System.out.println("Auflistung aller Termine:");
        for (Termin t : termine) {
            System.out.println(t.toString());
        }
        System.out.println("");

        ArrayList<Probe> proben = b.probenAuflisten(von, bis);

        System.out.println("Auflistung aller Proben:");
        for (Probe p : proben) {
            System.out.println(p.toString());
        }
        System.out.println("");

        ArrayList<Auftritt> auftritte = b.auftritteAuflisten(von, bis);

        System.out.println("Auflistung aller Auftritte:");
        for (Auftritt a : auftritte) {
            System.out.println(a.toString());
        }
        System.out.println("");
        
        /**
         * Termin loeschen und anschliessend wiederherstellen
         */
        b.terminLoeschen(p1);
        b.terminAendern(p3, new Probe(new Ort("P5 Keller", "Maxerstr. 32", 4), new GregorianCalendar(2002, 7, 8, 18, 0), "3:00", mitglieder, 70));
        b.terminAendern(p4, new Probe(new Ort("P6 Standort", "Maxerstr. 32", 4), new GregorianCalendar(2003, 7, 10, 18, 0), "3:00", mitglieder, 80));
        
        termine = b.termineAuflisten(von, bis);

        System.out.println("Auflistung der Termine nach dem Loeschen von P1,");
        System.out.println("sowie dem Aendern von P3 in P5 bzw P4 in P6:");
        for (Termin t : termine) {
            System.out.println(t.toString());
        }
        System.out.println("");
        
        b.terminWiederherstellen(p1);
        b.terminWiederherstellen(p3);
        b.terminWiederherstellen(p4);
        
        System.out.println("Dominiks Nachrichten:");
        
        ArrayList<String> messages = lukas.getMessages();
        
        for(String s : messages) {
            System.out.println(s);
        }
        System.out.println("");
        
        termine = b.termineAuflisten(von, bis);

        System.out.println("Auflistung der Termine nach der Wiederherstellung:");
        for (Termin t : termine) {
            System.out.println(t.toString());
        }
        System.out.println("");
        
        /**
         * Abstimmen ueber einen Auftritt
         */        
        
        System.out.println("\nAusgabe Abstimmung----------------------------------");
        System.out.println("Erwarteter Output: \nDer Termin, die Abstimmungsergebnisse inklusive Begruendungen der Mitglider,\nLukas ist dagegen und daher findet der Termin nicht statt");        
        System.out.println("----------------------------------------------------\n");
        
        Auftritt moeglAuftritt1 = new Auftritt(new Ort("a2 Gasometer", "Gasstr.666", 3000), new GregorianCalendar(1995, 9, 3, 18, 0), "2:00", mitglieder, 800);
        Abstimmung abstimmungProbe1 = b.abstimmenTermin(moeglAuftritt1);
        abstimmungProbe1.abstimmen(andreas, true, "passt");
        abstimmungProbe1.abstimmen(michael, true, "Leiwand!");
        abstimmungProbe1.abstimmen(lukas, false, "do kau i ned!!");
        abstimmungProbe1.abstimmen(dominik, true, "haut hi");
               
        /**
         * Ausgabe des Ergebnis der Abstimmung
         */
        String[] ergebnisAbstimmung = abstimmungProbe1.getResult();
        System.out.println(ergebnisAbstimmung[1]);
        
        if(ergebnisAbstimmung[0].equals("1")) {
            b.terminHinzufuegen(moeglAuftritt1);
            System.out.println(">> Termin findet statt");
        }
        else if(ergebnisAbstimmung[0].equals("0")) {
            System.out.println(">> Termin findet nicht statt");
        }
        System.out.println("");
   

        /**
         * Eine Bilanz ueber den gesuchten Zeitraum erstellen
         */
        System.out.println("\nAusgabe Bilanz------------------------------------");
        System.out.println("Erwarteter Output: \nEINZUFUEGEN");       
        System.out.println("----------------------------------------------------\n");
        
        TreeSet<Posten> bilanz = b.bilanzAuflisten(von, bis);
        
        for(Posten p : bilanz) {
            System.out.println(p.toString());
        }
        System.out.println("");
        
        b.terminLoeschen(a1);
        b.terminLoeschen(a2);
        
        bilanz = b.bilanzAuflisten(von, bis);
        
        for(Posten p : bilanz) {
            System.out.println(p.toString());
        }
        System.out.println("");
        
        b.terminAendern(p2, new Probe(new Ort("Do wo ma spuen", "Adresse", 40), new GregorianCalendar(2004, 5, 5), "04:00", mitglieder, 45));
        
        bilanz = b.bilanzAuflisten(von, bis);
        
        for(Posten p : bilanz) {
            System.out.println(p.toString());
        }
        System.out.println("");
        
        System.out.println("Gesamtkosten in diesem Zeitraum:" + b.kostenSummieren(true, true, von, bis) + " Euro");
        System.out.println("Gesamtumsatz in diesem Zeitraum:" + b.umsatzSummieren(true, true, von, bis) + " Euro");
        System.out.println("Macht einen Gesamtgewinn von:" + b.gewinnSummieren(true, true, true, von, bis) + " Euro");
        System.out.println("");
        
        /**
         * Einige Orte hinzufuegen und auflisten
         */
        System.out.println("\nAusgabe Orte----------------------------------------");
        System.out.println("Erwarteter Output:\nErste Abfrage gibt Orte mit mehr als 200 Plaetze aus,\nzweite mit mehr als 20 Plaetze\ndritte mit mehr als 100.000");
        System.out.println("----------------------------------------------------\n");
        
        System.out.println("\nOrte mit mehr als 200 Plaetzen:");
        ArrayList<Ort> orte = b.findeOrt(200);
        if(!orte.isEmpty()) {
            for (Ort o : orte) {
                    System.out.println(o);
            }
        }
        else  {System.out.println("Keinen Ort gefunden!");}
        
        System.out.println("\nOrte mit mehr als 20 Plaetzen:");
        orte = b.findeOrt(20);
        if(!orte.isEmpty()) {
            for (Ort o : orte) {
                    System.out.println(o);
            }
        }
        else  {System.out.println("Keinen Ort gefunden!");}
        
        System.out.println("\nOrte mit mehr als 100.000 Plätzen:");
        orte = b.findeOrt(100000);
        if(!orte.isEmpty()) {
            for (Ort o : orte) {
                    System.out.println(o);
            }
        }
        else  {System.out.println("Keinen Ort gefunden!");}
        
        System.out.println(""); 
    }
}