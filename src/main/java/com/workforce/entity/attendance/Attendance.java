package com.workforce.entity.attendance;

import com.workforce.entity.BaseEntity;
import com.workforce.entity.master.User;
import com.workforce.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendances")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE attendances SET is_deleted=true WHERE id=?")
public class Attendance extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    private Long staySeconds = 0L;

    @Convert(converter = AttendanceStatus.AttendanceStatusConverter.class)
    private AttendanceStatus status = AttendanceStatus.OPEN;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<AttendanceEvent> events = new ArrayList<>();
}