package com.workforce.entity.master;

import com.workforce.entity.BaseEntity;
import com.workforce.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted=true WHERE id=?")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id", nullable = false)
    private Designation designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private LocalDate dob;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String currentAddress;

    private String presentAddress;

    @Convert(converter = BloodGroup.BloodGroupConverter.class)
    private BloodGroup bloodGroup;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String profileIcon;

    @Column(nullable = false)
    private String password;

}