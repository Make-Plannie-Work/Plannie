package MakePlannieWork.Plannie.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "gebruikers")
public class Gebruiker implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gebruikersId;
    private String voornaam;
    private String achternaam;
    private String identifier; //UUID

    @Column(unique = true)
    private String email;

    private String wachtwoord;

    @Transient
    private String trancientWachtwoord;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    //CascadeType.PERSIST
            })
    @JoinTable(name = "gebruikers_rollen",
                joinColumns = @JoinColumn(name = "gebruikersid", referencedColumnName = "gebruikersId"),
                inverseJoinColumns = @JoinColumn(name = "rolId", referencedColumnName = "rolId"))
    private Collection<Rol> rollen;

    @ManyToMany(mappedBy = "groepsleden",
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    })
    private Set<Groep> groepen;

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

    public Set<Groep> getGroepen() {
        return groepen;
    }

    public void setGroepen(Set<Groep> groepen) {
        this.groepen = groepen;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Collection<Rol> getRollen() {
        return rollen;
    }

    public void setRollen(Collection<Rol> rollen) {
        this.rollen = rollen;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return wachtwoord;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
