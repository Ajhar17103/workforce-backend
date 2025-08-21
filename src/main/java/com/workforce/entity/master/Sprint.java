package com.workforce.entity.master;

import com.workforce.entity.BaseEntity;
import com.workforce.enums.BloodGroup;
import com.workforce.enums.SprintType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;


@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sprints")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE sprints SET is_deleted=true WHERE id=?")
public class Sprint extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer workingDays;

    private Integer dailyWorkingHrs;

    private Integer totalSprintHrs;

    @Convert(converter = SprintType.SprintTypeConverter.class)
    private SprintType sprintType;
}