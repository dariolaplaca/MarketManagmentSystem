package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.Shop;
import com.dlp.mms.Repositories.ShopRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    public Shop getById(Long id){
        return shopRepository.findById(id).orElse(null);
    }

    public List<Shop> getAll(){
        return shopRepository.findAll();
    }
    public void saveNew(Shop shop){
        shopRepository.saveAndFlush(shop);
    }

    public void remove(Long id){
        shopRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Shop shop){
        shopRepository.findById(id).ifPresent(shopFromRepo -> shopFromRepo.setName(shop.getName()));
    }

    public void addAccount(Long id, Account account){
        shopRepository.findById(id).ifPresent(shopFromRepo -> shopFromRepo.addAccount(account));
    }
}
