package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReisItemRepository extends JpaRepository<ReisItem, Integer> {

    ReisItem findGebruikerByReisItemId (Integer Id);

    Notitie findReisItemByReisItemId (Integer Id);
}
