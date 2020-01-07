package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "notitie")
public class Notitie extends ReisItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer notitieId;

    private String tekst;

    public Integer getNotitieId() {
        return notitieId;
    }

    public void setNotitieId(Integer notitieId) {
        this.notitieId = notitieId;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
