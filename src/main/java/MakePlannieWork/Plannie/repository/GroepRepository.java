package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface GroepRepository extends JpaRepository<Groep, Integer> {
    List<Groep> findByGroepsleden_GebruikersId(Integer gebruikersId);

    Groep findByGroepId(Integer groepId);
}
