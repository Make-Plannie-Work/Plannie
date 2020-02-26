package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.*;

@Entity
public class Notitie extends ReisItem {

    private String tekst;

    public String geefGeformatteerdeTekst() {
        return tekst.replace("\n", "<br/>");
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
