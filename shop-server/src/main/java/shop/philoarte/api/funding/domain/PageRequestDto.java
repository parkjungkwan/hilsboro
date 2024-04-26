package shop.philoarte.api.funding.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int perPage;

    public PageRequestDto() {
        this.page = 1;
        this.perPage = 6;

    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, perPage, sort);
    }

}
