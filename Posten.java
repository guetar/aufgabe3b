import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Stack;

/**
 *
 * @author Matthias
 */
public abstract class Posten implements Comparable<Posten> {

    //Invariante: wert!=0
    private int wert;
    //Invariante: kategorie ist entweder 1=Auftritt, 2=Probe, 3=sonstige Einnahme 
    //oder 4=sonstige Ausgabe;andere Werte sind nicht möglich
    //Schlecht: byte haette als Typ gereicht, int verbraucht unnoetig Speicher
    private Stack<Posten> stack;
    protected GregorianCalendar datum;

    //Vorbedingung: wert!=0.
    //datum!=null, datum enthaelt Informationen ueber Jahr,Monat,Tag,Stunde und Minute
    //Nachbedingung: beschreibung!=null, datum!=null, stack!=null
    public Posten(int wert, GregorianCalendar datum) {
        this.wert = wert;
        this.datum = datum;
        this.stack = new Stack<Posten>();
    }
    
    public Posten(GregorianCalendar datum) {
        this.datum = datum;
    }
    
  
    //Vorbedingung: p!=null
    //Nachbedingung: beschreibung, wert, datum und kategorie werden von p uebernommen
    public Posten(Posten p) {
        this.wert = p.getWert();
        this.datum = p.getDatum();
        this.stack = new Stack<Posten>();
    }
    
    //Vorbedingung: p!=null
    //Nachbedingung: this wird auf stack gelegt, beschreibung, wert, datum und 
    //kategorie werden von p uebernommen
    public Posten setPosten(Posten p) {
        stack.push(this);
        this.wert = p.getWert();
        this.datum = p.getDatum();
        return this;
    }
    
    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }
    
    public GregorianCalendar getDatum() {
        return datum;
    }
    
    public int getWert() {
        return wert;
    }
    
    
    
    //Nachbedingung: p wird auf stack gelegt
    protected void pushToStack(Posten p) {
        stack.push(p);
    }
    
    //Nachbedingung: stack verliert oberstes Element(falls vorhanden)
    protected Posten popFromStack() {
        if(!stack.empty()) {
            return stack.pop();
        }
        return null;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return sdf.format(datum.getTime()) + ",\t " + wert + " Euro\t";
    }
    
    @Override
    //Vorbedingung: p!=null
    public int compareTo(Posten p) {
        if(this.getDatum().before(p.getDatum())) {
            return -1;
        } else if(this.getDatum().after(p.getDatum())) {
            return 1;
        }
        return 0;
    }
}