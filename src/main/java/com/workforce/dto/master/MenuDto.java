package com.workforce.dto.master;


import com.workforce.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class MenuDto extends BaseDto {

    private String name;

    private String icon;

    private String path;

    private String menuType;

    private String parentId;
}
