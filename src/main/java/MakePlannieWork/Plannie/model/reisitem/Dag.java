package MakePlannieWork.Plannie.model.reisitem;

import java.util.ArrayList;

public class Dag {

    private Integer dagNummer;
    private ArrayList<ReisItem> reisItems;

    public Dag(Integer dagNummer) {
        setDagNummer(dagNummer);
    }

    public Dag(Integer dagNummer, ReisItem reisItem) {
        this(dagNummer);
        voegReisItemToe(reisItem);
    }

    // TODO methodes schrijven om algemene waardes over deze dag op te halen. Aantal activiteiten, startlocatie, etc etc.

    // Getters en Setters
    public Integer getDagNummer() {
        return dagNummer;
    }

    public void setDagNummer(Integer dagNummer) {
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
