package MakePlannieWork.Plannie.model;

import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groepId;

    private String groepsNaam;

    private Integer aanmaker;

    private String imagePath = "static/placeholder.png";

    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "GROEP_GEBRUIKER",
            joinColumns = {@JoinColumn(name = "groep_groepid")},
            inverseJoinColumns = {@JoinColumn(name = "gebruiker_gebruikersid")})
    private Set<Gebruiker> groepsleden = new HashSet<>();

    public int geefAantalGebruikersInGroep() {
        return groepsleden.size();
    }

    public Set<ReisItem> getReisItem() {
        return reisItem;
    }

    public void setReisItem(Set<ReisItem> reisItem) {
        this.reisItem = reisItem;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "groep_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ReisItem> reisItem;

    public Integer getGroepId() {
        return groepId;
    }

    public void setGroepId(Integer groepId) {
        this.groepId = groepId;
    }

    public String getGroepsNaam() {
        return groepsNaam;
    }

    public void setGroepsNaam(String groepsNaam) {
        this.groepsNaam = groepsNaam;
    }

    public Set<Gebruiker> getGroepsleden() {
        return groepsleden;
    }

    public void setGroepsleden(Set<Gebruiker> groepsleden) {
        this.groepsleden = groepsleden;
    }

    public Integer getAanmaker() {
        return aanmaker;
    }

    public void setAanmaker(Integer aanmaker) {
        this.aanmaker = aanmaker;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}