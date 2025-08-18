package com.workforce.entity.master;

import jakarta.persistence.*;
import lombok.*;
import com.workforce.entity.BaseEntity;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_menu_permissions")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE role_menu_permissions SET is_deleted=true WHERE id=?")
public class RoleMenuPermission extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private Boolean viewPermission = false;
    private Boolean addPermission = false;
    private Boolean updatePermission = false;
    private Boolean deletePermission = false;
}

