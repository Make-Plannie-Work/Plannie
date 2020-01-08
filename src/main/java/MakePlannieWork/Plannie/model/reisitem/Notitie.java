package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.*;

@Entity
@Table(name = "notitie")
public class Notitie extends ReisItem{

    private String tekst;

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
