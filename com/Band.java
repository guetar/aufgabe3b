/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author guetar
 */
public class Band
{    
    private ArrayList<Mitglied> mitglieder;
    private ArrayList<Song> repertoire;
    private ArrayList<Probe> proben;
    private ArrayList<Auftritt> auftritte;
    
    public Band()
    {
        mitglieder = new ArrayList<Mitglied>();
        repertoire = new ArrayList<Song>();
        proben = new ArrayList<Probe>();
        auftritte = new ArrayList<Auftritt>();
    }
    
    // Mitglieder
    
    public void mitglied_hinzufuegen(Mitglied _m)
    {
        mitglieder.add(_m);
    }
    
    public void mitglied_entfernen(Mitglied _m)
    {
        if(mitglieder.contains(_m))
        {
            mitglieder.remove(_m);
        }
    }
    
    public ArrayList<Mitglied> mitglieder_auflisten()
    {
        return mitglieder;
    }
    
    public ArrayList<Mitglied> mitglieder_auflisten(String _von, String _bis)
    {
        ArrayList<Mitglied> mitglieder_liste = new ArrayList<Mitglied>();
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date von = sdf.parse(_von);
            Date bis = sdf.parse(_bis);

            for(Mitglied m : mitglieder)
            {
                if(m.getVon().before(bis) && m.getBis().after(von))
                {
                    mitglieder_liste.add(m);
                }
            }
        }
        catch (ParseException ex) {}
        
        return mitglieder_liste;
    }
    
    // Repertoire
    
    public void song_hinzufuegen(Song _s)
    {
        repertoire.add(_s);
    }
    
    public void song_entfernen(Song _s)
    {
        if(repertoire.contains(_s))
        {
            repertoire.remove(_s);
        }
    }
    
    public ArrayList<Song> songs_auflisten()
    {
        return repertoire;
    }
    
    public ArrayList<Song> songs_auflisten(String _von)
    {
        ArrayList<Song> repertoire_liste = new ArrayList<Song>();
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date von = sdf.parse(_von);
        
            for(Song m : repertoire)
            {
                if(von.before(m.getVon()))
                {
                    repertoire_liste.add(m);
                }
            }
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        
        return repertoire_liste;
    }
    
    // Termine
    
    public ArrayList<Termin> termine_auflisten(String _von, String _bis)
    {
        ArrayList<Termin> termin_liste = new ArrayList<Termin>();
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date von = sdf.parse(_von);
            Date bis = sdf.parse(_bis);
            
            for(Termin t : proben)
            {
                if(von.before(t.getVon()) && bis.after(t.getVon()))
                {     
                    termin_liste.add(t);
                }
                else if(bis.before(t.getVon()))
                {
                    break;
                }
            }
            for(Termin t : auftritte)
            {
                if(von.before(t.getVon()) && bis.after(t.getVon()))
                {     
                    termin_liste.add(t);
                }
                else if(bis.before(t.getVon()))
                {
                    break;
                }
            }
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        
        return termin_liste;
    }
    
    // Proben
    
    public void probe_hinzufuegen(Probe _p)
    {
        for (int i=0; i<proben.size(); i++)
        {
            if(_p.getVon().before(proben.get(i).getVon()))
            {
                proben.add(i, _p);
                return;
            }
        }
        proben.add(_p);
    }
    
    public void probe_entfernen(Probe _p)
    {
        if(proben.contains(_p))
        {
            proben.remove(_p);
        }
    }
    
    public  ArrayList<Probe> proben_auflisten(String _von,  String _bis)
    {
        ArrayList<Probe> proben_liste = new ArrayList<Probe>();
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date von = sdf.parse(_von);
            Date bis = sdf.parse(_bis);
            
            for(Probe p : proben)
            {
                if(von.before(p.getVon()) && bis.after(p.getVon()))
                {     
                    proben_liste.add(p);
                }
                else if(bis.before(p.getVon()))
                {
                    break;
                }
            }
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        
        return proben_liste;
    }
    
    // Auftritte
    
    public void auftritt_hinzufuegen(Auftritt _a)
    {
        for (int i=0; i<proben.size(); i++)
        {
            if(_a.getVon().before(proben.get(i).getVon()))
            {
                auftritte.add(i, _a);
                return;
            }
        }
        auftritte.add(_a);
    }
    
    public void probe_entfernen(Auftritt _a)
    {
        if(auftritte.contains(_a))
        {
            auftritte.remove(_a);
        }
    }
    
    public  ArrayList<Auftritt> auftritte_auflisten(String _von,  String _bis)
    {
        ArrayList<Auftritt> auftritte_liste = new ArrayList<Auftritt>();
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date von = sdf.parse(_von);
            Date bis = sdf.parse(_bis);
            
            for(Auftritt a : auftritte)
            {
                if(von.before(a.getVon()) && bis.after(a.getVon()))
                {     
                    auftritte_liste.add(a);
                }
                else if(bis.before(a.getVon()))
                {
                    break;
                }
            }
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
        
        return auftritte_liste;
    }
    
    public int kosten_summieren(String _von,  String _bis)
    {
        int kosten=0;
        ArrayList<Probe> proben_kosten = proben_auflisten(_von, _bis);
        
        for(Probe p : proben_kosten)
        {
            kosten += p.getMiete();
        }
    
        return kosten;
    }
    
    public int umsatz_summieren(String _von,  String _bis)
    {
        int umsatz=0;
        ArrayList<Auftritt> auftritte_kosten = auftritte_auflisten(_von, _bis);
        
        for(Auftritt a : auftritte_kosten)
        {
            umsatz += a.getGage();
        }
    
        return umsatz;
    }
    
    public int gewinn_summieren(String _von,  String _bis)
    {
        return umsatz_summieren(_von, _bis) - kosten_summieren(_von, _bis);
    }
}