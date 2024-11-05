package com.lawencon.jobportal.model.request;

import java.util.List;
import com.lawencon.jobportal.validation.annotation.NotNullParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingRequest {
    @NotNullParam(fieldName = "Paging Size")
    private Integer page;

    @NotNullParam(fieldName = "Page Size")
    private Integer pageSize;

    private List<SortBy> sortBy;

    public Integer getPage() {
        return page - 1;
    }
}

