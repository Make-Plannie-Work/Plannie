package MakePlannieWork.Plannie.model;

import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groep {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groepId;

    private String groepsNaam;

    private Gebruiker aanmaker;

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

    public Gebruiker getAanmaker() {
        return aanmaker;
    }

    public void setAanmaker(Principal principal) {
        this.aanmaker = gebruikerRepository.findGebruikerByEmail(principal.getName());
    }
}
