package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroepRepository extends JpaRepository<Groep, Integer> {
    List<Groep> findByGroepsleden_GebruikersId(Integer gebruikersId);

    Optional<Groep> findByIdentifier(String uuid);
}
