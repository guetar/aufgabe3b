
import java.util.GregorianCalendar;

/**
 *
 * @author Matthias
 */

public class SonstigerPosten extends Posten{
    //IV: kategorie entweder 3 oder 4
    private int kategorie;
    private String beschreibung;
    
    //VB: wert!=0, beschreibung!=null, datum!=null
    //NB: kategorie!=0, beschreibung!=null
    public SonstigerPosten(int wert, String beschreibung, GregorianCalendar datum){
        super(wert, datum);
        kategorie=(wert>0)?3:4;
        this.beschreibung=beschreibung;
    }
    
    @Override
    //NB: return 3 oder 4
    public int getKategorie() {
        return kategorie;
    }

    //NB: return!=null
    public String getBeschreibung(){
        return beschreibung;
    }
    
    @Override
    //NB: return!=null
    public String toString(){
        return beschreibung+" "+super.toString();
    }
}
