package com.dlp.mms.ProductOrder;

import com.dlp.mms.DTOs.ResponseStringDTO;
import com.dlp.mms.OrderReceipt.OrderReceiptService;
import com.dlp.mms.Product.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/product-order")
public class ProductOrderController {
    @Autowired
    ProductOrderService productOrderService;
    @Autowired
    OrderReceiptService orderReceiptService;
    @Autowired
    ProductService productService;

    public ProductOrderController(ProductOrderService productOrderService, OrderReceiptService orderReceiptService, ProductService productService) {
        this.productOrderService = productOrderService;
        this.orderReceiptService = orderReceiptService;
        this.productService = productService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductOrder>> getAll() {
        return ResponseEntity.ok(productOrderService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductOrder> getById(@PathVariable Long id) {
        ProductOrder productOrderToReturn = productOrderService.getById(id);
        if (productOrderToReturn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productOrderToReturn);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStringDTO> add(@RequestParam Long orderReceiptId, @RequestParam Long productId, @RequestParam int quantity) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderReceipt(orderReceiptService.getById(orderReceiptId));
        productOrder.setProduct(productService.getById(productId));
        productOrder.setQuantity(quantity);
        productOrder.setUnitPrice(productOrder.getProduct().getPrice() * quantity);
        productOrderService.saveNew(productOrder);
        return ResponseEntity.ok(new ResponseStringDTO("Product Order created!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStringDTO> delete(@PathVariable Long id) {
        productOrderService.remove(id);
        return ResponseEntity.ok(new ResponseStringDTO("Product Order removed!"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ResponseStringDTO> deleteAll() {
        productOrderService.removeAll();
        return ResponseEntity.ok(new ResponseStringDTO("All Product Orders removed!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStringDTO> update(@PathVariable Long id, @RequestBody ProductOrder productOrder) {
        productOrderService.update(id, productOrder);
        return ResponseEntity.ok(new ResponseStringDTO("Product Order updated!"));
    }
}