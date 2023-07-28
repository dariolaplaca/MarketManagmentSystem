package com.dlp.mms.Entities;

import com.dlp.mms.Auditables.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class OrderReceipt extends AuditableEntity<User> {
    @Column
    private LocalDateTime orderDate;
    @ManyToOne
    private Account cashier;
    @Column
    private double totalAmount;
}
