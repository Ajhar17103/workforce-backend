package com.workforce.entity.leave;

import com.workforce.entity.BaseEntity;
import com.workforce.entity.master.User;
import com.workforce.enums.LeaveFor;
import com.workforce.enums.LeaveStatus;
import com.workforce.enums.LeaveType;
import com.workforce.enums.TaskTracker;
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
@Table(name = "leaves-requests")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE leaves-requests SET is_deleted=true WHERE id=?")
public class LeaveRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String fiscalYear;

    @Convert(converter = LeaveType.LeaveTypeConverter.class)
    private LeaveType leaveType = LeaveType.UNKNOWN;

    @Convert(converter = LeaveFor.LeaveForConverter.class)
    private LeaveFor leaveFor = LeaveFor.UNKNOWN;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Double totalDay;

    private String reason;

    private String attchmentPath;

    @Convert(converter = LeaveStatus.LeaveStatusConverter.class)
    private LeaveStatus leaveStatus = LeaveStatus.PENDING;

    private String remarks;
}