package MakePlannieWork.Plannie.repository;

/**
 * @Author Tabitha Krist
 */

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.GebruikerVerificatieToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerVerificatieRepository extends JpaRepository<GebruikerVerificatieToken, Integer> {

    GebruikerVerificatieToken findByToken(String token);

    GebruikerVerificatieToken findByGebruiker(Gebruiker gebruiker);

}
