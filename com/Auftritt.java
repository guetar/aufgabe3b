/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Matthias
 */
public class Auftritt extends Termin
{
    private int gage;
    
    public Auftritt(String _ort, String _von, String _bis, int _gage)
    {   
        super(_ort, _von, _bis);
        gage = _gage;
    }

    @Override
    public String toString()
    {
        return terminToString() + " " + gage + " Euro Gage";
    }
    
    public int getGage()
    {
        return gage;
    }
}
