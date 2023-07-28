package com.dlp.mms.Services;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Repositories.AccountRepository;
import com.dlp.mms.Config.PasswordManager;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordManager passwordManager;

    public AccountService(AccountRepository accountRepository, PasswordManager passwordManager){
        this.accountRepository = accountRepository;
        this.passwordManager = passwordManager;
    }

    public Account getById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public void saveNew(Account account){
        account.setPassword(passwordManager.encodePassword(account.getPassword()));
        account.setUsername(account.getEmail());
        accountRepository.saveAndFlush(account);
    }

    public void remove(Long id){
        accountRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Account account){
        accountRepository.findById(id).ifPresent(accountFromRepo -> {
            accountFromRepo.setFirstName(account.getFirstName());
            accountFromRepo.setLastName(account.getLastName());
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
