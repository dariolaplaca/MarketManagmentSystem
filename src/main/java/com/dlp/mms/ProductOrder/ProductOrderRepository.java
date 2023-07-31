package com.dlp.mms.ProductOrder;

import com.dlp.mms.ProductOrder.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
