package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;

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

    @Query(value = "SELECT * FROM gebruikers WHERE achternaam LIKE %:tekst% OR voornaam LIKE %:tekst% OR email LIKE %:tekst% AND gebruikers_id NOT IN (SELECT gebruiker_gebruikersid FROM groep_gebruiker WHERE groep_groepid = :groepid) LIMIT 10;", nativeQuery = true)
    List<Gebruiker> findGebruikers(
            @Param("tekst") String gebruikers,
            @Param("groepid") Integer groepId);
}
