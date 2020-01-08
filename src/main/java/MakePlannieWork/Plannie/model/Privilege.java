package MakePlannieWork.Plannie.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer privilegeId;

    private String privilegenaam;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Rol> rollen;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegenaam() {
        return privilegenaam;
    }

    public void setPrivilegenaam(String privilegenaam) {
        this.privilegenaam = privilegenaam;
    }

    public Collection<Rol> getRollen() {
        return rollen;
    }

    public void setRollen(Collection<Rol> rollen) {
        this.rollen = rollen;
    }
}
