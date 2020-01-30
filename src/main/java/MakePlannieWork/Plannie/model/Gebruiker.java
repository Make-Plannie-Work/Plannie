package MakePlannieWork.Plannie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Indexed
@Table(name = "gebruikers")
public class Gebruiker implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gebruikersId;
    private String voornaam;
    private String achternaam;
    @JsonIgnore
    private String identifier; //UUID

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String wachtwoord;

    @Transient
    @JsonIgnore
    private String trancientWachtwoord;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gebruikers_rollen", joinColumns = @JoinColumn(name = "gebruikersid", referencedColumnName = "gebruikersId"), inverseJoinColumns = @JoinColumn(name = "rolId", referencedColumnName = "rolId"))
    @JsonIgnore
    private Collection<Rol> rollen;

    @ManyToMany(mappedBy = "groepsleden")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return wachtwoord;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
