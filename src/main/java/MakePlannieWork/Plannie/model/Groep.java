package MakePlannieWork.Plannie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Groep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groepId;
}
