package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Groep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroepRepository extends JpaRepository<Groep, Integer> {
    List<Groep> findByGroepsleden_GebruikersId(Integer gebruikersId);

    Groep findByGroepId(Integer groepId);
}
