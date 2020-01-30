package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.GebruikerVerificatieToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.stream.Stream;

public interface GebruikerVerificatieRepository extends JpaRepository<GebruikerVerificatieToken, Integer> {

    GebruikerVerificatieToken findByToken(String token);

    GebruikerVerificatieToken findByGebruiker(Gebruiker gebruiker);

    Stream<GebruikerVerificatieToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from GebruikerVerificatieToken where expiryDate <=?1")
    void deleteAllExpiredSince(Date now);
}
