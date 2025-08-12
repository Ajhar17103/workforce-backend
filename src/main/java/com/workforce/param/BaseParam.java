package com.workforce.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class BaseParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -103658650614029839L;

    private static final Set<String> baseFields = new HashSet<>();

    private static final String EQUAL = "=";

    @Schema(hidden = true)
    private Integer pageNo;

    @Schema(hidden = true)
    private Integer pageSize = 20;

    @Schema(hidden = true)
    private String sortBy;

    @Schema(hidden = true)
    private String sign;

    private Boolean active = Boolean.TRUE;
}
