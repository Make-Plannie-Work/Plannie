package MakePlannieWork.Plannie.model.reisitem;

import javax.persistence.Entity;

@Entity
public class Locatie extends ReisItem {

    // TODO wat voor soort data is een locatie?
    // ReisItem naam is de omschrijving van de locatie
    // ReisItem adres wordt opgegeven door de gebruiker, voor navigatie?

    private String adres;
    private double latitude;
    private double longitude;
}
