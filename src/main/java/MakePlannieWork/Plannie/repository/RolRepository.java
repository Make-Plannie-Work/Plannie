package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByRolNaam(String rolNaam);
}
