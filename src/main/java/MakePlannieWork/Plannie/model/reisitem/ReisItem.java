package MakePlannieWork.Plannie.model.reisitem;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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

    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
            name = "reis_item",
            referencedColumnName = "reisItemId"
    )
    private Set<ReisItem> reisItems;

    @ManyToOne
    @JoinColumn(name = "reis_item")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ReisItem gekoppeldeReisItem;

    public void voegReisItemToe(ReisItem reisItem) {
        reisItems.add(reisItem);
    }

    public Integer getReisItemId() {
        return reisItemId;
    }

    public void setReisItemId(Integer reisItemId) {
        this.reisItemId = reisItemId;
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
}
