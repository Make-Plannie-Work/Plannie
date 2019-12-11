package MakePlannieWork.Plannie.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groepId;

    private String groepsNaam;

    @ManyToMany
    @JoinTable(
            name="GROEP_GEBRUIKER",
            joinColumns = { @JoinColumn(name="groep_groepid")},
            inverseJoinColumns = {@JoinColumn(name="gebruiker_gebruikersid")})
    private Set<Gebruiker> groepsleden = new HashSet<>();

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
}
