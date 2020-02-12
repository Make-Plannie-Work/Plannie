package MakePlannieWork.Plannie.model.dto;

import javax.persistence.Column;

public class ActiviteitDTO {

    private String naam;
    private String startDatum;
    private String locatie;

    private Double budget;

    //activiteit
    private String soortActiviteit;
    private String omschrijving;

    //notitie
    private String tekst;

    //adres
    private String adres;
    private double latitude;
    private double longitude;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getSoortActiviteit() {
        return soortActiviteit;
    }

    public void setSoortActiviteit(String soortActiviteit) {
        this.soortActiviteit = soortActiviteit;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

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

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
