package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    List<Gebruiker> findByGroepen_groepsNaam(String groepsNaam);
}
