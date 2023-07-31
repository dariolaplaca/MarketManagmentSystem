package com.dlp.mms.OrderReceipt;

import com.dlp.mms.DTOs.ResponseStringDTO;
import com.dlp.mms.OrderReceipt.OrderReceipt;
import com.dlp.mms.OrderReceipt.OrderReceiptService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/order-receipt")
public class OrderReceiptController {
    @Autowired
    OrderReceiptService orderReceiptService;

    public OrderReceiptController(OrderReceiptService orderReceiptService) {
        this.orderReceiptService = orderReceiptService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OrderReceipt>> getAll() {
        return ResponseEntity.ok(orderReceiptService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderReceipt> getById(@PathVariable Long id) {
        OrderReceipt orderReceiptToReturn = orderReceiptService.getById(id);
        if (orderReceiptToReturn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderReceiptToReturn);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStringDTO> add(@RequestBody OrderReceipt orderReceipt) {
        orderReceiptService.saveNew(orderReceipt);
        return ResponseEntity.ok(new ResponseStringDTO("Order Receipt created!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStringDTO> delete(@PathVariable Long id) {
        orderReceiptService.remove(id);
        return ResponseEntity.ok(new ResponseStringDTO("Order Receipt removed!"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ResponseStringDTO> deleteAll() {
        orderReceiptService.removeAll();
        return ResponseEntity.ok(new ResponseStringDTO("All Order Receipts removed!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStringDTO> update(@PathVariable Long id, @RequestBody OrderReceipt orderReceipt) {
        orderReceiptService.update(id, orderReceipt);
        return ResponseEntity.ok(new ResponseStringDTO("Order Receipt updated!"));
    }
}