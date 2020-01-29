package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {

    Optional<Gebruiker> findByEmail (String email);

    Gebruiker findGebruikerByEmail (String email);

    Boolean existsByEmail (String email);

    Gebruiker findGebruikerByIdentifier(String identifier);

    Gebruiker findGebruikerByGebruikersId (Integer Id);

    List<Gebruiker> findGebruikersByEmail(String email);

    List<Gebruiker> findByGroepen_groepId(Integer groepId);

    @Query(value = "SELECT * FROM gebruikers WHERE achternaam LIKE %?1% OR voornaam LIKE %?1% OR email LIKE %?1%", nativeQuery = true)
    List<Gebruiker> findGebruikers(String gebruikers);
}
