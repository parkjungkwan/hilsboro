package hillsboro.philoarte.scalar.services;

import hillsboro.philoarte.scalar.types.CategoryDto;

import java.util.List;


public interface CategoryService {
    List<CategoryDto> findAllCategory();
}
