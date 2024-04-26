package shop.philoarte.api.category.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.philoarte.api.common.util.ModelMapperUtils;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;

    public static CategoryDto of(Category category) {
        CategoryDto categoryDto = ModelMapperUtils.getModelMapper().map(category, CategoryDto.class);
        return categoryDto;
    }
}
