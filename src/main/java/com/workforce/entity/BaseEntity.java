package com.workforce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;


@Data
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4413996581711007095L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "isActive")
    @Builder.Default
    private Boolean active = Boolean.TRUE;

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "CreatedBy", length = 50)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "UpdatedBy", length = 50)
    private String updatedBy;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "DeletedBy", length = 50)
    private String deletedBy;

    @Column(name = "isDeleted")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;
}