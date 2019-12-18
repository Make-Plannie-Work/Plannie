package MakePlannieWork.Plannie.model.reisitem;

import MakePlannieWork.Plannie.model.Groep;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
    private String datum;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "groep_id", referencedColumnName = "groep_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Groep groep;

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

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Groep getGroep() {
        return groep;
    }

    public void setGroep(Groep groep) {
        this.groep = groep;
    }



}
