package com.workforce.entity.leave;

import com.workforce.entity.BaseEntity;
import com.workforce.entity.master.User;
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
@Table(name = "allocated_leaves")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE allocated_leaves SET is_deleted=true WHERE id=?")
public class AllocatedLeave extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String fiscalYear;

    private Double totalSickLeave;

    private Double takenSickLeave;

    private Double totalCasualLeave;

    private Double takenCasualLeave;

    private Double totalAnnualLeave;

    private Double takenAnnualLeave;

    private Double paidLeave;
}