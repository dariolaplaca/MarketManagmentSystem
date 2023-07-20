package com.dlp.mms.Controllers;

import com.dlp.mms.DTOs.ResponseStringDTO;
import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.Product;
import com.dlp.mms.Services.AccountService;
import com.dlp.mms.Services.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product productToReturn = productService.getById(id);
        if (productToReturn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productToReturn);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStringDTO> add(@RequestBody Product product) {
        productService.saveNew(product);
        return ResponseEntity.ok(new ResponseStringDTO("Product created!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStringDTO> delete(@PathVariable Long id) {
        productService.remove(id);
        return ResponseEntity.ok(new ResponseStringDTO("Product removed!"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ResponseStringDTO> deleteAll() {
        productService.removeAll();
        return ResponseEntity.ok(new ResponseStringDTO("All Products removed!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStringDTO> update(@PathVariable Long id, @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.ok(new ResponseStringDTO("Product updated!"));
    }
}