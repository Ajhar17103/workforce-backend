package com.workforce.entity.master;

import com.workforce.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
//import org.hibernate.envers.Audited;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@ToString
@Entity
@Table(name = "menus")
@SQLDelete(sql = "UPDATE menus SET is_deleted=1 WHERE id=?")
@Where(clause = "is_deleted=0")
//@Audited(auditParents = {BaseEntity.class})
public class Menu extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String icon;

    private String path;

    private String menuType;

    private String parentId;
}
