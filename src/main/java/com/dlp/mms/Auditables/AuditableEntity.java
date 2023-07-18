package com.dlp.mms.Auditables;
import com.dlp.mms.Enums.RecordStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<User>{
    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Hidden
    @Column(name = "record_status")
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    @Hidden
    @Column(name = "modified_by")
    @LastModifiedBy
    private User modifiedBy;

    @Hidden
    @Column(name = "created_by")
    @CreatedBy
    private User createdBy;

    @Hidden
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Hidden
    @Column(name = "created_on")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private LocalDateTime createdOn;

    public AuditableEntity(){
        this.recordStatus = RecordStatus.ACTIVE;
    }

}