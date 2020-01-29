package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.Entity;

@Entity
public class Locatie extends ReisItem {

    // TODO wat voor soort data is een locatie?

    private String adres;

    private double coordinates;
}
