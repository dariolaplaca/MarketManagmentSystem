package com.dlp.mms.Security;

import com.dlp.mms.Entities.Account;
import com.dlp.mms.Repositories.AccountRepository;
import com.dlp.mms.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordManager {

    @Autowired
    AccountRepository accountRepository;

    PasswordManager(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    PasswordManager(){
    }

    public String encodePassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public boolean checkPassword(String password, Long accountId){
        Account account = accountRepository.findById(accountId).orElse(null);
        boolean doesPasswordMatch = false;
        if(account != null){
            doesPasswordMatch = account.getPassword().equals(encodePassword(password));
        }
        return doesPasswordMatch;
    }

}
