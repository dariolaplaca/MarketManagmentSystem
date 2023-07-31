package com.dlp.mms.ProductOrder;

import com.dlp.mms.Auditables.AuditableEntity;
import com.dlp.mms.OrderReceipt.OrderReceipt;
import com.dlp.mms.Product.Product;
import jakarta.persistence.*;
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
    @ManyToOne
    private OrderReceipt orderReceipt;
    @ManyToOne
    private Product product;
    @Column
    private int quantity;
    @Column
    private double unitPrice;
}
