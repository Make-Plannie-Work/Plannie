package MakePlannieWork.Plannie.model.dto;

public class ActiviteitDTO {

    private String naam;
    private String startDatum;
    private String locatie;
    private Double budget;

    //activiteit
    private String soortActiviteit;
    private String omschrijving;

    //notitie
    private boolean notitieNodig;
    private String notitieNaam;
    private String notitieTekst;

    //adres
    private boolean locatieNodig;
    private String locatieNaam;
    private String locatieAdres;
    private double locatieLatitude;
    private double locatieLongitude;

    public boolean isNotitieNodig() {
        return notitieNodig;
    }

    public void setNotitieNodig(boolean notitieNodig) {
        this.notitieNodig = notitieNodig;
    }

    public String getNotitieNaam() {
        return notitieNaam;
    }

    public void setNotitieNaam(String notitieNaam) {
        this.notitieNaam = notitieNaam;
    }

    public boolean isLocatieNodig() {
        return locatieNodig;
    }

    public void setLocatieNodig(boolean locatieNodig) {
        this.locatieNodig = locatieNodig;
    }

    public String getLocatieNaam() {
        return locatieNaam;
    }

    public void setLocatieNaam(String locatieNaam) {
        this.locatieNaam = locatieNaam;
    }

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

    public String getNotitieTekst() {
        return notitieTekst;
    }

    public void setNotitieTekst(String notitieTekst) {
        this.notitieTekst = notitieTekst;
    }

    public String getLocatieAdres() {
        return locatieAdres;
    }

    public void setLocatieAdres(String locatieAdres) {
        this.locatieAdres = locatieAdres;
    }

    public double getLocatieLatitude() {
        return locatieLatitude;
    }

    public void setLocatieLatitude(double locatieLatitude) {
        this.locatieLatitude = locatieLatitude;
    }

    public double getLocatieLongitude() {
        return locatieLongitude;
    }

    public void setLocatieLongitude(double locatieLongitude) {
        this.locatieLongitude = locatieLongitude;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
