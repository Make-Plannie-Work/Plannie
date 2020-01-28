package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.Entity;

@Entity
public class Budget extends ReisItem {

    public int getBedrag() {
        return bedrag;
    }

    public void setBedrag(int bedrag) {
        this.bedrag = bedrag;
    }

    private int bedrag;
}
