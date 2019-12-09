package repository;

import model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
}
