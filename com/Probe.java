/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Matthias
 */
public class Probe extends Termin
{
    private int miete;
    
    public Probe(String _ort, String _von, String _bis, int _miete)
    {
        super(_ort, _von, _bis);
        miete = _miete;
    }

    @Override
    public String toString()
    {
        return terminToString() + " " + miete + " Euro Miete";
    }
    
    public int getMiete()
    {
        return miete;
    }
}
