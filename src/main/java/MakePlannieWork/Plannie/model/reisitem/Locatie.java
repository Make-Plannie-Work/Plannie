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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
