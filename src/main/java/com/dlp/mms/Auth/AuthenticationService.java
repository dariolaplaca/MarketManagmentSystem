package com.dlp.mms.Auth;

import com.dlp.mms.Config.JwtService;
import com.dlp.mms.Account.Account;
import com.dlp.mms.Enums.UserRole;
import com.dlp.mms.Account.AccountRepository;
import com.dlp.mms.Mail.MailDetails;
import com.dlp.mms.Mail.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final MailServiceImpl mailService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .username(request.getEmail())
                .build();
        accountRepository.saveAndFlush(user);
        var jwtToken = jwtService.generateToken(user);
        MailDetails registrationMail = this.getRegistrationMail(user, jwtToken);
        mailService.sendMail(registrationMail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private MailDetails getRegistrationMail(Account user, String jwtToken) {
        MailDetails mailDetails = new MailDetails();
        mailDetails.setRecipient(user.getEmail());
        mailDetails.setSubject("Registration Token");
        mailDetails.setMessageBody(
                "Dear " + user.getFirstName() + " " + user.getLastName() + "\n" +
                "Thanks for choosing our MarketManagementSystemService!\nHere for you we provide a token to enable your registration, just insert in your account to get the token for the authentication in our services!\n" +
                "TOKEN: " + jwtToken + "\nIf you have any issues don't hesitate to contact our client support team!\n\nMarket Management System");
        return mailDetails;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user = accountRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
