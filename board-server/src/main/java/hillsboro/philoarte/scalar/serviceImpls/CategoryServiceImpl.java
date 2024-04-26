package hillsboro.philoarte.scalar.serviceImpls;

import java.util.List;
import java.util.stream.Collectors;

import hillsboro.philoarte.scalar.entities.Category;
import hillsboro.philoarte.scalar.repositories.CategoryDao;
import hillsboro.philoarte.scalar.services.CategoryService;
import hillsboro.philoarte.scalar.types.CategoryDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao repo;

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> categories = repo.findAll();
        return categories.stream().map(category -> CategoryDto.of(category)).collect(Collectors.toList());
    }

}
