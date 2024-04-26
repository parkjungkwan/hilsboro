package shop.philoarte.api.category.service;

import java.util.List;

import shop.philoarte.api.category.domain.CategoryDto;

public interface CategoryService {
    List<CategoryDto> findAllCategory();
}
