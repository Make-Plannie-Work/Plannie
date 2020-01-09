package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Poll extends ReisItem {

    @OneToMany
    @JoinTable(
            name = "POLL_STEMMEN",
            joinColumns = {@JoinColumn(name = "reisItemId")}
            )
    private Set<PollOptie> pollOpties = new HashSet<>();

    public Set<PollOptie> getPollOpties() {
        return pollOpties;
    }

    public void setPollOpties(Set<PollOptie> pollOpties) {
        this.pollOpties = pollOpties;
    }
}
