package com.dlp.mms.Product;

import com.dlp.mms.Auditables.AuditableEntity;
import com.dlp.mms.Enums.ProductCategory;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column
    private ProductCategory category;
}
