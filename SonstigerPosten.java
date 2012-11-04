
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
    private int kategorie;
    private String beschreibung;
    
    public SonstigerPosten(int wert, int kategorie, String beschreibung, GregorianCalendar datum){
        super(wert, datum);
        this.kategorie=kategorie;
        this.beschreibung=beschreibung;
    }
    
    @Override
    public int getKategorie() {
        return kategorie;
    }
    
    public String getBeschreibung(){
        return beschreibung;
    }
    
    @Override
    public String toString(){
        return beschreibung+" "+super.toString();
    }
}
