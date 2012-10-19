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
public class Mitglied
{    
    private String name;
    private String tel;
    private String instrument;
    private Date von;
    private Date bis;
    
    public Mitglied(String _name, String _tel, String _instrument, String _von, String _bis)
    {
        name = _name;
        tel = _tel;
        instrument = _instrument;
            
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            von = sdf.parse(_von);
            bis = sdf.parse(_bis);
        }
        catch (ParseException ex)
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
    
    @Override
    public String toString()
    {
        return name + " " + tel + " " + instrument;
    }
}
