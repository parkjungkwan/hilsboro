package shop.philoarte.api.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.philoarte.api.category.domain.Category;
import shop.philoarte.api.category.domain.CategoryDto;
import shop.philoarte.api.category.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> categories = repo.findAll();
        return categories.stream().map(category -> CategoryDto.of(category)).collect(Collectors.toList());
    }

}
