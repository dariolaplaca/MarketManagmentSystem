package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Repositories.AccountRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account getById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public void saveNew(Account account){
        accountRepository.saveAndFlush(account);
    }

    public void remove(Long id){
        accountRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Account account){
        accountRepository.findById(id).ifPresent(accountFromRepo -> {
            accountFromRepo.setUsername(account.getUsername());
            accountFromRepo.setPassword(account.getPassword());
            accountFromRepo.setEmail(account.getEmail());
            accountFromRepo.setUserRole(account.getUserRole());
            accountRepository.saveAndFlush(accountFromRepo);
        });
    }

    public void removeAll() {
        accountRepository.deleteAll();
    }
}
