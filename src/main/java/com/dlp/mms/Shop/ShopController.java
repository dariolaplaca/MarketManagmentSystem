package com.dlp.mms.Shop;

import com.dlp.mms.DTOs.ResponseStringDTO;
import com.dlp.mms.Account.Account;
import com.dlp.mms.Shop.Shop;
import com.dlp.mms.Shop.ShopService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<Shop>> getAll(){
        return ResponseEntity.ok(shopService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Shop> getById(@PathVariable Long id){
        Shop shopToReturn = shopService.getById(id);
        if(shopToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopToReturn);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStringDTO> add(@RequestBody Shop shop){
        shopService.saveNew(shop);
        return ResponseEntity.ok(new ResponseStringDTO("Shop created!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStringDTO> delete(@PathVariable Long id){
        shopService.remove(id);
        return ResponseEntity.ok(new ResponseStringDTO("Shop removed!"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ResponseStringDTO> deleteAll(){
        shopService.removeAll();
        return ResponseEntity.ok(new ResponseStringDTO("All shops removed!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStringDTO> update(@PathVariable Long id, @RequestBody Shop shop){
        shopService.update(id, shop);
        return ResponseEntity.ok(new ResponseStringDTO("Shop updated!"));
    }

    @PutMapping("/add-account/{shopId}/{accountId}")
    public ResponseEntity<ResponseStringDTO> addAccountToShop(@PathVariable Long shopId, @PathVariable Long accountId){
        ResponseStringDTO responseString = shopService.addAccount(shopId, accountId);
        if(responseString.getMessage().contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseString);
        }
        return ResponseEntity.ok(responseString);
    }

    @GetMapping("/get-employees-list/{shopId}")
    public ResponseEntity<List<Account>> getEmployeesList(@PathVariable Long shopId){
        List<Account> employeesList = shopService.getAllEmployees(shopId);
        if(employeesList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeesList);
    }


}
