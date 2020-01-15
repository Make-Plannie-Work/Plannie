package MakePlannieWork.Plannie.model.reisitem;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "poll_optie")
public class PollOptie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pollOptieId;

    private String stemOptie;

    // Hiermee worden de poll opties gesorteerd op de volgorde waarop de gebruiker ze op wilde slaan.
    private int optieIndex;

    @ManyToOne
    @JoinColumn(name = "reisItemId")
    private Poll poll;

    @ManyToMany
    private Set<Gebruiker> stemmen = new HashSet<>();

    public int geefAantalStemmen() {
        return stemmen.size();
    }

    // Kijkt of een gebruiker op deze optie gestemd heeft.
    public boolean gebruikerHeeftGestemd(int gebruikerID) {
        boolean gestemd = false;
        Iterator<Gebruiker> gebruikers = stemmen.iterator();
        while (gebruikers.hasNext() && !gestemd) {
            if (gebruikers.next().getGebruikersId() == gebruikerID) {
                gestemd = true;
            }
        }
        return gestemd;
    }

    public void verwijderStem(Gebruiker gebruiker) {
        stemmen.remove(gebruiker);
    }

    public void voegStemToe(Gebruiker gebruiker) {
        stemmen.add(gebruiker);
    }

    // Getters en Setters
    public Integer getPollOptieId() {
        return pollOptieId;
    }

    public void setPollOptieId(Integer pollOptieId) {
        this.pollOptieId = pollOptieId;
    }

    public String getStemOptie() {
        return stemOptie;
    }

    public void setStemOptie(String stemOptie) {
        this.stemOptie = stemOptie;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Set<Gebruiker> getStemmen() {
        return stemmen;
    }

    public void setStemmen(Set<Gebruiker> stemmen) {
        this.stemmen = stemmen;
    }

    public int getOptieIndex() {
        return optieIndex;
    }

    public void setOptieIndex(int optieIndex) {
        this.optieIndex = optieIndex;
    }
}
