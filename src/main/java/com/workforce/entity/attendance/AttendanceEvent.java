package com.workforce.entity.attendance;

import com.workforce.entity.BaseEntity;
import com.workforce.enums.AttendanceType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance_events")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE attendance_events SET is_deleted=true WHERE id=?")
public class AttendanceEvent extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false)
    private Attendance attendance;

    @Convert(converter = AttendanceType.AttendanceTypeConverter.class)
    private AttendanceType type = AttendanceType.UNKNOWN;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private Double lat;

    private Double lng;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String remarks;
}