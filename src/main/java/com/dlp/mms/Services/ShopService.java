package com.dlp.mms.Services;

import com.dlp.mms.DTOs.ResponseStringDTO;
import com.dlp.mms.Entities.Account;
import com.dlp.mms.Entities.Shop;
import com.dlp.mms.Repositories.ShopRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@NoArgsConstructor
@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AccountService accountService;

    public ShopService(ShopRepository shopRepository, AccountService accountService){
        this.shopRepository = shopRepository;
        this.accountService = accountService;
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

    public ResponseStringDTO addAccount(Long shopId, Long accountId){
        ResponseStringDTO responseString = new ResponseStringDTO("Account can't be added");
        Shop shop = this.getById(shopId);
        if(shop != null){
            Account account = accountService.getById(accountId);
            if(account != null){
                shop.addAccount(account);
                shopRepository.saveAndFlush(shop);
                responseString.setMessage("Account " + accountId + " successfully added to shop " + shop.getName());
            }
            else{
                responseString.setMessage("Account not found!");
            }
        } else {
            responseString.setMessage("Shop not found!");
        }
        return responseString;
    }

    public void removeAll() {
        shopRepository.deleteAll();
    }

    public List<Account> getAllEmployees(Long id){
        Shop shop = this.getById(id);
        if(shop != null){
            return shop.getEmployeesList();
        }
        return null;
    }
}
