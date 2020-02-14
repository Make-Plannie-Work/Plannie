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

    public String geefDatum() {
        return reisItems.get(0).geefGeformatteerdeStartDatum();
    }

    public String geefDagTitel() {
        return "Dag " + dagNummer;
    }

    public String geefOmschrijving() {
        StringBuilder omschrijving = new StringBuilder();
        for (ReisItem item : reisItems) {
            if (item instanceof Poll) {
                omschrijving.append("Poll, ").append(((Poll) item).pollStatus());
            } else if (item instanceof Activiteit) {
                omschrijving.append(((Activiteit) item).getSoortActiviteit()).append(", ").append(item.getNaam());
            } else if (item instanceof Locatie) {
                omschrijving.append("Locatie, ").append(((Locatie) item).getAdres());
            }
            omschrijving.append("\n");
        }
        return omschrijving.toString();
    }

    public Double geefBudget() {
        Double budget = 0.0;
        for (ReisItem item : reisItems) {
            budget += item.berekenTotaalBudget();
        }
        return budget;
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
