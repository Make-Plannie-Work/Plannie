package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.Entity;

@Entity
public class Activiteit extends ReisItem {

    private String soortActiviteit;

    public String getSoortActiviteit() {
        return soortActiviteit;
    }

    public void setSoortActiviteit(String soortActiviteit) {
        this.soortActiviteit = soortActiviteit;
    }
}
