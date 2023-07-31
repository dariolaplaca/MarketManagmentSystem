package com.dlp.mms.Account;

import com.dlp.mms.DTOs.ResponseStringDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id){
        Account accountToReturn = accountService.getById(id);
        if(accountToReturn == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountToReturn);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseStringDTO> add(@RequestBody Account account){
       accountService.saveNew(account);
       return ResponseEntity.ok(new ResponseStringDTO("Account created!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStringDTO> delete(@PathVariable Long id){
        accountService.remove(id);
        return ResponseEntity.ok(new ResponseStringDTO("Account removed!"));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ResponseStringDTO> deleteAll(){
        accountService.removeAll();
        return ResponseEntity.ok(new ResponseStringDTO("All accounts removed!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStringDTO> update(@PathVariable Long id, @RequestBody Account account){
        accountService.update(id, account);
        return ResponseEntity.ok(new ResponseStringDTO("Account updated!"));
    }

}
