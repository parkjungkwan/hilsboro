package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

}
