package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNaam(String rol);

    Rol findRolByRolNaam(String Rol);

    boolean existsByRolNaam(String rolNaam);
}
