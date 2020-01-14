package MakePlannieWork.Plannie.model.reisitem;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Poll extends ReisItem {

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "poll")
    private Set<PollOptie> pollOpties = new HashSet<>();

    public void voegPollOptieToe(PollOptie pollOptie) {
        this.pollOpties.add(pollOptie);
    }

    public int geefTotaalAantalStemmen() {
        int aantal = 0;
        for (PollOptie optie : pollOpties) {
            aantal += optie.getStemmen().size();
        }
        return aantal;
    }

    public void gebruikerStemt(int pollOptieId, Gebruiker gebruiker) {
        for (PollOptie optie : pollOpties) {
            if (optie.getPollOptieId() == pollOptieId) {
                optie.voegStemToe(gebruiker);
            } else {
                optie.verwijderStem(gebruiker);
            }
        }
    }

    // Getters en Setters
    public Set<PollOptie> getPollOpties() {
        return pollOpties;
    }

    public void setPollOpties(Set<PollOptie> pollOpties) {
        this.pollOpties = pollOpties;
    }
}
