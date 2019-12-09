package repository;

import model.reisitem.ReisItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReisItemRepository extends JpaRepository<ReisItem, Integer> {
}
