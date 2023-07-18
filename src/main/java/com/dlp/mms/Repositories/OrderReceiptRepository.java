package com.dlp.mms.Repositories;

import com.dlp.mms.Entities.OrderReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReceiptRepository extends JpaRepository<OrderReceipt, Long> {
}
