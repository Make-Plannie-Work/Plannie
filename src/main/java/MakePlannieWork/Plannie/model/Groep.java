package MakePlannieWork.Plannie.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Groep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groepId;

    private String groepsNaam;

    @ManyToMany
    private Set<Gebruiker> groepsleden;

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
