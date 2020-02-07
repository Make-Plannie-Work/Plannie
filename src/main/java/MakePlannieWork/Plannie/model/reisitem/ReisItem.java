package MakePlannieWork.Plannie.model.reisitem;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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
        return startDatum;
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
