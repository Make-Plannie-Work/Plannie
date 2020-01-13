package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.WachtwoordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Stream;

public interface WachtwoordResetRepository extends JpaRepository<WachtwoordResetToken, Integer> {

    WachtwoordResetToken findByToken(String token);

    WachtwoordResetToken findByUser(Gebruiker gebruiker);

    Stream<WachtwoordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from WachtwoordResetToken where expiryDate <=?1")
    void deleteAllExpiredSince(Date now);
}
