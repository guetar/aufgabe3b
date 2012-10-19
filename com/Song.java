/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.GregorianCalendar;

/**
 *
 * @author guetar
 */
public class Song
{
    private String name;
    private String laenge;
    private GregorianCalendar von;
    
    public Song(String _name, String _laenge, GregorianCalendar _von)
    {
        name = _name;
        laenge = _laenge;
        von = _von;
    }
    
    public GregorianCalendar getVon()
    {
        return von;
    }
    
    @Override
    public String toString()
    {
        return name + " " + laenge;
    }
}
