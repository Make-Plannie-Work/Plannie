package MakePlannieWork.Plannie.model.reisitem;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * ReisItem class aangemaakt. Is composite. Heeft 1 op veel relatie met Groep.
 */

@Entity
@Table(name = "reis_item")
public class ReisItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reisItemId;

    private String naam;
    private String startDatum;
    private String eindDatum;
    private String locatie;
    private Integer aanmaker;
    private String imagePath = "static/placeholder.png";

    @Column(nullable = true)
    private Double budget;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gekoppeldeReisItem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ReisItem> reisItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reis_item")
    private ReisItem gekoppeldeReisItem;

    public double berekenTotaalBudget() {
        if (budget == null) {
            budget = 0.0;
        }

        Double totaalBudget = budget;
        for (ReisItem item : reisItems) {
            if (reisItems.isEmpty()) {
                return totaalBudget;
            } else {
                totaalBudget += item.berekenTotaalBudget();
            }
        }
        return totaalBudget;
    }

    // Methode om de overkoepelende reis te geven, inplaats van het eerstvolgende reisItem.
    public Integer vindHoofdReisId() {
        if (gekoppeldeReisItem == null) {
            return reisItemId;
        } else {
            return gekoppeldeReisItem.vindHoofdReisId();
        }
    }

    // Methode om de set van ReisItems gesorteerd terug te geven.
    public ArrayList<ReisItem> geefReisGesorteerdDatum() {
        ArrayList<ReisItem> itemsGesorteerd = new ArrayList<>(reisItems);
        itemsGesorteerd.sort(Comparator.comparing(ReisItem::getStartDatum));
        return itemsGesorteerd;
    }

    // Methode om alle subItems van dit item in dagen te rangschikken.
    // Dag 0 bevat alle niet-activiteiten.
    public ArrayList<Dag> geefDagenOverzicht() {
        ArrayList<Dag> dagen = new ArrayList<>();
        dagen.add(new Dag(0));
        ArrayList<ReisItem> reisItem = geefReisGesorteerdDatum();

        String laatsteDatum = "";
        // Alle reisItems bijlangs
        for (ReisItem item : reisItem) {
            if (!(item instanceof Activiteit)) {
                // Alle niet-Activiteit items worden op dag 0 ingedeeld.
                dagen.get(0).voegReisItemToe(item);
            } else {
                // Alle activiteiten worden in dagen opgedeeld.
                if (item.getStartDatum().equals(laatsteDatum)) {
                    // Als de datum hetzelfde is als de vorige item, wordt deze activiteit bij de nieuwste dag ingevoegd.
                    dagen.get(dagen.size() - 1).voegReisItemToe(item);
                } else {
                    // Als de datum een nieuwe is, wordt er een nieuwe dag aangemaakt, met bijhorend dagnummer.
                    Integer dagNummer = 1; // TODO dagnummer laten uitrekenen aan de hand van de eerste datum.


                    Dag dag = new Dag(dagNummer,item);
                    dagen.add(dag);
                }

                laatsteDatum = item.getStartDatum();
            }
        }

        return dagen;
    }

    // Methode om de datum van dit, en alle onderliggende reisItems te wijzigen.
    public void wijzigCompleteReisDatum(String nieuweDatum) {
        if (!this.startDatum.equals(nieuweDatum)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                // Verschil in dagen uitrekenen.
                Date nieuw = format.parse(nieuweDatum);
                Date oud = format.parse(this.startDatum);
                long verschil = nieuw.getTime() - oud.getTime();
                verschil = TimeUnit.DAYS.convert(verschil, TimeUnit.MILLISECONDS);

                // De datum van dit reisItem, en alle onderliggende reisItems aanpassen.
                wijzigDatum(verschil);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    // De datum van dit reisItem, en alle onderliggende reisItems aanpassen.
    public void wijzigDatum(long verschil) {
        DateTimeFormatter datumFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datum = LocalDate.parse(startDatum, datumFormatter);
        // Het verschil in dagen bij de startDatum optellen.
        datum = datum.plusDays(verschil);
        this.startDatum = datumFormatter.format(datum);

        for (ReisItem item : getReisItems()) {
            item.wijzigDatum(verschil);
        }
    }

    // Methode om een startdatum voor een nieuw reisItem te geven.
    public String geefNieuwStartDatum() {
        if (this.startDatum == null) {
            // Als dit reisItem zelf geen startdatum heeft, wordt er gecontroleerd of er een gekoppeldReisItem is.
            if (this.gekoppeldeReisItem != null) {
                // De gekoppeldeReisItem wordt om de startDatum gevraagd.
                return this.gekoppeldeReisItem.geefNieuwStartDatum();
            } else {
                // Als er geen gekoppeldeReisItem is, wordt de startdatum op de datum van vandaag gezet.
                DateTimeFormatter datumFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime vandaag = LocalDateTime.now();
                return datumFormatter.format(vandaag);
            }
        } else {
            // Als dit reisItem een startdatum heeft, geeft hij deze terug, om klaar te zetten in de JSP's, voor een nieuw ReisItem.
            return this.startDatum;
        }
    }

    public void voegReisItemToe(ReisItem reisItem) {
        if (reisItems == null) {
            reisItems = new HashSet<>();
        }
        reisItems.add(reisItem);
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public ReisItem getGekoppeldeReisItem() {
        return gekoppeldeReisItem;
    }

    public void setGekoppeldeReisItem(ReisItem gekoppeldeReisItem) {
        this.gekoppeldeReisItem = gekoppeldeReisItem;
    }

    public Integer getReisItemId() {
        return reisItemId;
    }

    public void setReisItemId(Integer reisItemId) {
        this.reisItemId = reisItemId;
    }

    public Integer getId() {
        return this.reisItemId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Integer getAanmaker() {
        return aanmaker;
    }

    public void setAanmaker(Integer aanmaker) {
        this.aanmaker = aanmaker;
    }

    public String getStartDatum() {
        return geefNieuwStartDatum();
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(String eindDatum) {
        this.eindDatum = eindDatum;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Set<ReisItem> getReisItems() {
        return reisItems;
    }

    public void setReisItems(Set<ReisItem> reisItems) {
        this.reisItems = reisItems;
    }

    public ReisItem getGekoppeldeReisItemId() {
        return gekoppeldeReisItem;
    }

    public void setGekoppeldeReisItemId(ReisItem gekoppeldeReisItemId) {
        this.gekoppeldeReisItem = gekoppeldeReisItemId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Met deze methode haal je het reisItemId op van het hoogste reisItem.
    public Integer getHoofdReisItemId() {
        if (gekoppeldeReisItem == null) {
            return this.reisItemId;
        }
        return gekoppeldeReisItem.getHoofdReisItemId();
    }
}
