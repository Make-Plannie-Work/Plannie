package MakePlannieWork.Plannie.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class GebruikerVerificatieToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gebruikerVerificatieTokenId;

    private String token;

    @OneToOne(targetEntity = Gebruiker.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "gebruikersId")
    private Gebruiker gebruiker;

    private Date expiryDate;

    private Date berekenExpiryDate(final int expiryTijdInMinuten) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTijdInMinuten);
        return new Date(calendar.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = berekenExpiryDate(EXPIRATION);
    }

    public GebruikerVerificatieToken() {
    }

    public GebruikerVerificatieToken(String token) {
        this.token = token;
        this.expiryDate = berekenExpiryDate(EXPIRATION);
    }

    public GebruikerVerificatieToken(String token, Gebruiker gebruiker) {
        this.token = token;
        this.gebruiker = gebruiker;
        this.expiryDate = berekenExpiryDate(EXPIRATION);
    }

    public Integer getGebruikerVerificatieTokenId() {
        return gebruikerVerificatieTokenId;
    }

    public void setGebruikerVerificatieTokenId(Integer gebruikerVerificatieTokenId) {
        this.gebruikerVerificatieTokenId = gebruikerVerificatieTokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
