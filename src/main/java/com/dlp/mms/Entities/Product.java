package com.dlp.mms.Entities;

import com.dlp.mms.Auditables.AuditableEntity;
import com.dlp.mms.Enums.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AuditableEntity<User> {
    @Column
    private String name;
    @Column
    private int quantityInStock;
    @Column
    private double price;
    @Column
    private ProductCategory category;
}
