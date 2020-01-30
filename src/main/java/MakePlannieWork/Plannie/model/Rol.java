package MakePlannieWork.Plannie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rolId;

    @ManyToMany(mappedBy = "rollen")
    @JsonIgnore
    private Collection<Gebruiker> gebruikers;

    private String rolNaam;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rollen_privileges", joinColumns = @JoinColumn(name = "rolId", referencedColumnName = "rolId"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilegeId"))
    private Collection<Privilege> privileges;

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public Collection<Gebruiker> getGebruikers() {
        return gebruikers;
    }

    public void setGebruikers(Collection<Gebruiker> gebruikers) {
        this.gebruikers = gebruikers;
    }

    public String getRolNaam() {
        return rolNaam;
    }

    public void setRolNaam(String rolNaam) {
        this.rolNaam = rolNaam;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
