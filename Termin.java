import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Matthias
 */
public abstract class Termin extends Posten {

    private Ort ort;
    private String dauer;
    private Stack<Termin> stack;
    private HashSet<Mitglied> teilnehmer;

    //Vorbedingung: ort, datum, dauer und teilnehmer != null
    public Termin(int wert,Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer) {
        super(wert, datum);
        this.ort = ort;
        this.dauer = dauer;
        this.stack = new Stack<Termin>();
        this.teilnehmer = teilnehmer;
        
        for(Mitglied m : teilnehmer) {
            m.addTermin(this);
        }
    }

    //Vorbedingung: Termin != null
    public Termin(Termin t) {
        super(t.getWert(), t.getDatum());
        this.ort = t.getOrt();
        this.dauer = t.getDauer();
        this.stack = new Stack<Termin>();
        this.teilnehmer = new HashSet<Mitglied>(t.getTeilnehmer());
    }

    //Vorbedingung: Termin != null
    //Nachbedingung: this = Kopie von Termin
    public Termin setTermin(Termin t) {
        super.setPosten(t);
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
        this.teilnehmer = t.getTeilnehmer();
        return this;
    }
    
    //Nachbedingung: return:
    //true, falls der Teilnehmer hinzugefuegt werden und ihm der Termin uebergeben werden konnte
    //false andernfalls.
    public boolean teilnehmerHinzufuegen(Mitglied m) {
        boolean ok = teilnehmer.add(m);
        if (ok) {
            m.addTermin(this);
        }
        return ok;
    }
    
    //Nachbedingung: return:
    //true, falls der Teilnehmer entfernt und ihm der Termin entzogen werden konnte,
    //false andernfalls.
    public boolean teilnehmerEntfernen(Mitglied m) {
        boolean ok = teilnehmer.remove(m);
        if (ok) {
            m.terminLoeschen(this);
        }
        return ok;
    }

    //Nachbedingung: return dauer
    public String getDauer() {
        return dauer;
    }
    
    //Nachbedingung: return ort
    public Ort getOrt() {
        return ort;
    }
    
    //Nachbedingung: return teilnehmer
    public HashSet<Mitglied> getTeilnehmer() {
        return teilnehmer;
    }
    
    //Vorbedingung: Termin != null
    public void pushToStack(Termin t) {
        super.pushToStack(t);
    }
    
    @Override
    //Nachbedingung: return alten Termin
    public Termin popFromStack() {
        return (Termin) super.popFromStack();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(datum.getTime()) + " - " + dauer+" "+getWert()+" Euro";
    }
}