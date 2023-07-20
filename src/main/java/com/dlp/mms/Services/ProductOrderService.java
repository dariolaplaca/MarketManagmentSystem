package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.Product;
import com.dlp.mms.Entities.ProductOrder;
import com.dlp.mms.Repositories.ProductOrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class ProductOrderService {
    ProductOrderRepository productOrderRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository){
        this.productOrderRepository = productOrderRepository;
    }

    public ProductOrder getById(Long id){
        return productOrderRepository.findById(id).orElse(null);
    }

    public List<ProductOrder> getAll(){
        return productOrderRepository.findAll();
    }
    public void saveNew(ProductOrder productOrder){
        productOrderRepository.saveAndFlush(productOrder);
    }

    public void remove(Long id){
        productOrderRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ProductOrder productOrder){
        productOrderRepository.findById(id).ifPresent(productOrderFromRepo -> {
            productOrderFromRepo.setOrderReceipt(productOrder.getOrderReceipt());
            productOrderFromRepo.setProduct(productOrder.getProduct());
            productOrderFromRepo.setQuantity(productOrder.getQuantity());
            productOrderFromRepo.setUnitPrice(productOrder.getUnitPrice());
            productOrderRepository.saveAndFlush(productOrderFromRepo);
        });
    }

    public void removeAll() {
        productOrderRepository.deleteAll();
    }
}
