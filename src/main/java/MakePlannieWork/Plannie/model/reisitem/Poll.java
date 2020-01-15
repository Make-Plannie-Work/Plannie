package MakePlannieWork.Plannie.model.reisitem;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Poll extends ReisItem {

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "poll")
    private Set<PollOptie> pollOpties = new HashSet<>();

    public void voegPollOptieToe(PollOptie pollOptie) {
        this.pollOpties.add(pollOptie);
    }

    // Kijkt hoeveel mensen er in totaal op deze Poll gestemd hebben.
    public int geefTotaalAantalStemmen() {
        int aantal = 0;
        for (PollOptie optie : pollOpties) {
            aantal += optie.getStemmen().size();
        }
        return aantal;
    }

    // Kijkt of een gebruiker op deze poll gestemd heeft.
    public boolean gebruikerHeeftGestemd(int gebruikerId) {
        boolean gestemd = false;
        Iterator<PollOptie> opties = pollOpties.iterator();
        while (opties.hasNext() && !gestemd) {
            if (opties.next().gebruikerHeeftGestemd(gebruikerId)) {
                gestemd = true;
            }
        }
        return gestemd;
    }

    // Kijkt welke opties op dit moment de meeste stemmen hebben.
    public ArrayList<PollOptie> geefWinnendeOpties() {
        ArrayList<PollOptie> winnendeOpties = new ArrayList<>();
        int aantalStemmen = 0;
        for (PollOptie optie : pollOpties) {
            if (aantalStemmen < optie.geefAantalStemmen()) {
                // Als een optie meer stemmen heeft, wordt de lijst leeggegooid, en wordt dit de nieuwe winnaar.
                winnendeOpties.clear();
                winnendeOpties.add(optie);
                aantalStemmen = optie.geefAantalStemmen();
            } else if (aantalStemmen != 0 && aantalStemmen == optie.geefAantalStemmen()) {
                // Als een optie een gedeelde winnaar is, wordt deze toegevoegd aan de lijst.
                winnendeOpties.add(optie);
            }
        }

        return winnendeOpties;
    }

    // Een gebruiker wordt toegevoegd als stemmer aan een pollOptie, en verwijderd uit alle andere pollOpties.
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
