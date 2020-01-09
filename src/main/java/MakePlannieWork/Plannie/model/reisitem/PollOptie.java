package MakePlannieWork.Plannie.model.reisitem;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "poll_optie")
public class PollOptie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pollOptieId;
    private String stemOptie;

    @OneToOne
    @JoinColumn(name = "reisItemId")
    private Poll poll;

    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "POLL_STEMMEN",
            joinColumns = {@JoinColumn(name = "pollOptieId")},
            inverseJoinColumns = {@JoinColumn(name = "gebruiker_gebruikersid")})
    private Set<Gebruiker> stemmen = new HashSet<>();

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
}
