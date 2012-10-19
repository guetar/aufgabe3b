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
 * @author Matthias
 */
public abstract class Termin
{    
    private String ort;
    private Date von;
    private Date bis;
    private SimpleDateFormat sdf;
    
    public Termin(String _ort, String _von, String _bis)
    {
        ort = _ort;
        
        try
        {
            sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            von = sdf.parse(_von);
            bis = sdf.parse(_bis);
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
    
    public Date getBis()
    {
        return bis;
    }
    
    public String terminToString()
    {
        return ort + " " + sdf.format(von) + " - " + sdf.format(bis);
    }
}
