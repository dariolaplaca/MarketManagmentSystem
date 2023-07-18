package com.dlp.mms.Entities;

import com.dlp.mms.Auditables.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder extends AuditableEntity<User> {
    @OneToMany
    private OrderReceipt orderReceipt;
    @OneToMany
    private Product product;
    @Column
    private int quantity;
    @Column
    private double unitPrice;
}