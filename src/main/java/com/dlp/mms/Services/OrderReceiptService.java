package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.OrderReceipt;
import com.dlp.mms.Repositories.OrderReceiptRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class OrderReceiptService {
    @Autowired
    OrderReceiptRepository orderReceiptRepository;

    public OrderReceiptService(OrderReceiptRepository orderReceiptRepository){
        this.orderReceiptRepository = orderReceiptRepository;
    }

    public OrderReceipt getById(Long id){
        return orderReceiptRepository.findById(id).orElse(null);
    }

    public List<OrderReceipt> getAll(){
        return orderReceiptRepository.findAll();
    }
    public void saveNew(OrderReceipt orderReceipt){
        orderReceiptRepository.saveAndFlush(orderReceipt);
    }

    public void remove(Long id){
        orderReceiptRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, OrderReceipt orderReceipt){
        orderReceiptRepository.findById(id).ifPresent(orderReceiptFromRepo -> {
            orderReceiptFromRepo.setCashier(orderReceipt.getCashier());
            orderReceiptFromRepo.setOrderDate(orderReceipt.getOrderDate());
            orderReceiptFromRepo.setTotalAmount(orderReceipt.getTotalAmount());
            orderReceiptRepository.saveAndFlush(orderReceiptFromRepo);
        });
    }

}
