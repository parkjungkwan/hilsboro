package shop.philoarte.api.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.philoarte.api.category.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
