package com.workforce.entity.master;

import lombok.*;
import com.workforce.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menus")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE menus SET is_deleted=true WHERE id=?")
public class Menu extends BaseEntity {

    private UUID parentId;

    private String parentMenu;

    @Column(nullable = false, unique = true)
    private String name;

    private String icon;

    private String path;
}