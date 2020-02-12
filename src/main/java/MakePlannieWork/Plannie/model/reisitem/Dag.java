package MakePlannieWork.Plannie.model.reisitem;

import java.util.ArrayList;

public class Dag {

    private long dagNummer;
    private ArrayList<ReisItem> reisItems;

    public Dag(long dagNummer) {
        setDagNummer(dagNummer);
    }

    public Dag(long dagNummer, ReisItem reisItem) {
        this(dagNummer);
        voegReisItemToe(reisItem);
    }

    // TODO methodes schrijven om algemene waardes over deze dag op te halen. Aantal activiteiten, startlocatie, etc etc.
    public String geefDagTitel() {
        return "Dag " + dagNummer;
    }

    // Getters en Setters
    public long getDagNummer() {
        return dagNummer;
    }

    public void setDagNummer(long dagNummer) {
        this.dagNummer = dagNummer;
    }

    public void voegReisItemToe(ReisItem reisItem) {
        if (reisItems == null) {
            reisItems = new ArrayList<>();
        }
        reisItems.add(reisItem);
    }

    public ArrayList<ReisItem> getReisItems() {
        return reisItems;
    }

    public void setReisItems(ArrayList<ReisItem> reisItems) {
        this.reisItems = reisItems;
    }
}
