/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.Band;
import com.Probe;
import com.Termin;
import com.Song;
import com.Mitglied;
import com.Auftritt;
import java.util.ArrayList;

/**
 *
 * @author guetar
 */
public class Test
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        String von="21.02.1985";
        String bis="30.09.2012";       
        Band b = new Band();
        
        b.mitglied_hinzufuegen(new Mitglied("Andreas Kodolsky", "0676/7787986", "Gitarre", "24.02.2000", "24.02.2010"));
        b.mitglied_hinzufuegen(new Mitglied("Michael Preis", "0664/8798653", "Bass", "06.04.2004", "06.04.2006"));
        ArrayList<Mitglied> mitglieder = b.mitglieder_auflisten(von,bis);
        
        System.out.println("Auflistung der Gruppenmitglieder:");       
        for(Mitglied m : mitglieder)
        {
            System.out.println(m.toString());
        }
        System.out.println("");
        
        b.song_hinzufuegen(new Song("I love you", "04:33", "02.02.2012"));
        b.song_hinzufuegen(new Song("I loved you but now I love another woman", "03:22", "04.02.2012"));
        ArrayList<Song> repertoire = b.songs_auflisten();
         
        System.out.println("Auflistung des Repertoires:");        
        for(Song s : repertoire)
        {
            System.out.println(s.toString());
        }
        System.out.println("");
        
        b.probe_hinzufuegen(new Probe("Musterstr. 23", "05.06.1995 18:00", "05.06.1995 20:00", 5));
        b.probe_hinzufuegen(new Probe("Musterstr. 23", "12.03.2012 18:00", "12.03.2012 20:00", 20));
        b.auftritt_hinzufuegen(new Auftritt("Stadthalle", "23.08.2012 18:00", "23.08.2012 22:00", 500));        
        b.auftritt_hinzufuegen(new Auftritt("Gasometer", "25.02.1987 22:00", "26.02.1987 05:00", 800));
        
        ArrayList<Termin> termine = b.termine_auflisten(von,bis); 
        
        System.out.println("Auflistung aller Termine:");        
        for(Termin t : termine)
        {
            System.out.println(t.terminToString());
        }
        System.out.println("");
        
        ArrayList<Probe> proben = b.proben_auflisten(von,bis); 
        
        System.out.println("Auflistung aller Proben:");        
        for(Probe p : proben)
        {
            System.out.println(p.toString());
        }
        System.out.println("");
        
        ArrayList<Auftritt> auftritte = b.auftritte_auflisten(von,bis); 
        
        System.out.println("Auflistung aller Auftritte:");        
        for(Probe p : proben)
        {
            System.out.println(p.toString());
        }
        System.out.println("");
        
        System.out.println("Gesamtkosten in diesem Zeitraum:" + b.kosten_summieren(von,bis)+" Euro");
        System.out.println("Gesamtumsatz in diesem Zeitraum:" + b.umsatz_summieren(von,bis)+" Euro");
        System.out.println("Macht einen Gesamtgewinn von:" + b.gewinn_summieren(von,bis)+" Euro");
        System.out.println("");
    }
}
