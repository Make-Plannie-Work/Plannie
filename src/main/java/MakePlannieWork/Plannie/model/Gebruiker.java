package MakePlannieWork.Plannie.model;

import javax.persistence.*;

@Entity
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gebruikersId;
    private String voornaam;
    private String achternaam;
    private String email;
    private String wachtwoord;

    @Transient
    private String trancientWachtwoord;

    public String toString() {
        return getGebruikersId() + ":\t" + voornaam + " " + achternaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getTrancientWachtwoord() {
        return trancientWachtwoord;
    }

    public void setTrancientWachtwoord(String trancientWachtwoord) {
        this.trancientWachtwoord = trancientWachtwoord;
    }

    public Integer getGebruikersId() {
        return gebruikersId;
    }

    public void setGebruikersId(Integer gebruikersId) {
        this.gebruikersId = gebruikersId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
