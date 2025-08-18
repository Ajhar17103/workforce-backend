package com.workforce.entity.master;

import com.workforce.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "designations")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE designations SET is_deleted=1 WHERE id=?")
public class Designation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false, unique = true)
    private String name;
}

