package com.workforce.param;


import com.workforce.utils.common.PageHelper;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"pageNo","pageSize","sortBy"})
public class PageableParam {
    private Integer pageNo;
    private Integer pageSize = 20;
    private String sortBy;
    public boolean isPageable() {
        return pageNo != null;
    }
    public Pageable getPageable() {
        return PageHelper.getPageRequest(this);
    }
    public Sort getSort() {
        return PageHelper.getSort(this);
    }
}
