
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthias
 */

public class SonstigerPosten extends Posten{
    private String beschreibung;
    
    public SonstigerPosten(int wert, GregorianCalendar datum, String beschreibung){
    super(wert, datum);
    this.beschreibung=beschreibung;
    }
    
    public String getBeschreibung(){
    return beschreibung;
    }
    
    @Override
    public String toString(){
    return beschreibung+" "+super.toString();
    }
}
