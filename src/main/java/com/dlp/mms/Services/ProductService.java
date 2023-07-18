package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.Product;
import com.dlp.mms.Entities.Shop;
import com.dlp.mms.Repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public void saveNew(Product product){
        productRepository.saveAndFlush(product);
    }

    public void remove(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Product product){
        productRepository.findById(id).ifPresent(productFromRepo -> {
                    productFromRepo.setName(product.getName());
                    productFromRepo.setQuantityInStock(product.getQuantityInStock());
                    productFromRepo.setPrice(product.getPrice());
                    productFromRepo.setCategory(product.getCategory());
                    productRepository.saveAndFlush(productFromRepo);
                });
    }


}
