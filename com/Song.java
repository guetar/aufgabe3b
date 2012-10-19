/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author guetar
 */
public class Song
{
    private String name;
    private Date laenge;
    private Date von;
    private SimpleDateFormat sdf_laenge;
    private SimpleDateFormat sdf_von;
    
    public Song(String _name, String _laenge, String _von)
    {
        this.name = _name;
        
        try
        {
            sdf_laenge = new SimpleDateFormat("mm:ss");
            laenge = sdf_laenge.parse(_laenge);
            sdf_von = new SimpleDateFormat("dd.MM.yyyy");
            von = sdf_von.parse(_von);
        }
        catch(ParseException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Date getVon()
    {
        return von;
    }
    
    @Override
    public String toString()
    {
        return name + " " + sdf_laenge.format(laenge);
    }
}
