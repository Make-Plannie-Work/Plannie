package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {

    Optional<Gebruiker> findByEmail (String email);

    Gebruiker findGebruikerByEmail (String email);

    List<Gebruiker> findGebruikersByEmail(String email);

    List<Gebruiker> findByGroepen_groepsNaam(String groepsNaam);
}
