package com.example.Banking_Application_Developement.service;

import com.example.Banking_Application_Developement.config.AccountConfiguration;
import com.example.Banking_Application_Developement.model.AccountUser;
import com.example.Banking_Application_Developement.model.DTO.LoginRequest;
import com.example.Banking_Application_Developement.model.DTO.LoginResponse;
import com.example.Banking_Application_Developement.model.Role;
import com.example.Banking_Application_Developement.repository.AccountUserRepository;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUserService {
//    @Autowired
    private final AccountUserRepository accountUserRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    private MessageService messageService;
    private BankAccountService bankAccountService;
    private AccountConfiguration accountConfiguration;

    public AccountUserService(AccountUserRepository accountUserRepository,
                              PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              JWTService jwtService,
                              MessageService messageService,
                              BankAccountService bankAccountService,
                              AccountConfiguration accountConfiguration) {
        this.accountUserRepository = accountUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.bankAccountService = bankAccountService;
        this.accountConfiguration = accountConfiguration;
    }

    // Get all Account users
    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    // Get Account user by id
    public ResponseEntity<AccountUser> getAccountUserById(int id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
    }
    // Get Account user by email
    public ResponseEntity<AccountUser> getAccountUserByUsername(String username){
        return new ResponseEntity<>(accountUserRepository.getByUsername(username), HttpStatus.OK);
    }
    // Get Account user by PhoneNumber
    public ResponseEntity<AccountUser> getAccountUserByPhoneNumber(String phoneNumber){
        return new ResponseEntity<>(accountUserRepository.getByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    // Create New Account User

    public ResponseEntity<AccountUser> addNewAccountUser(AccountUser accountUser) throws MessagingException {
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
//        accountUser.setRole(accountUser.getRole());
        accountUser.setRole(Role.USER);
        messageService.registrationNotification(accountUser.getUsername(), "Dear " + accountUser.getFirstName() + "\n"+
                "You have been successfully registered  to our Banking System."
        );
        AccountUser savedUser = accountUserRepository.save(accountUser);
        bankAccountService.createBankAccount(savedUser);
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    public ResponseEntity<LoginResponse> authenticate(LoginRequest loginRequest) throws MessagingException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        if(authentication != null){
            AccountUser accountUser = accountUserRepository.getByUsername(loginRequest.getUsername());
            String token = jwtService.createToken(accountUser);

            messageService.loginNotification(accountUser.getUsername(), "Dear " + accountUser.getFirstName() + "\n"+
                    "There has been a successful login into your Banking Account. Please if you did not log in"+
                    "call the following number: 01-12345678, 080-123-456-78"
                    );

            return new ResponseEntity<>(LoginResponse.builder().accountUser(accountUser).token(token).build(), HttpStatus.OK);
        }
        return null;
    }

    // Update an existing Account User
    public ResponseEntity<AccountUser> updateAccountUser(int id, AccountUser accountUser){
        AccountUser accountUserFromDb = accountUserRepository.findById(id).get();

        accountUserFromDb.setFirstName(accountUser.getFirstName());
        accountUserFromDb.setMiddleName(accountUser.getMiddleName());
        accountUserFromDb.setLastName(accountUser.getLastName());
        accountUserFromDb.setUsername(accountUser.getUsername());
        accountUserFromDb.setPassword(accountUser.getPassword());
        accountUserFromDb.setPhoneNumber(accountUser.getPhoneNumber());
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

    // Update a particular filed of an existing Account User
//    public ResponseEntity<AccountUser> updateOneFieldOfAccountUser(int id, Map<String, Object> accountUser){
//        Optional<AccountUser> accountUserFromDb = accountUserRepository.findById(id);
//        if(accountUserFromDb.isPresent()){
//            AccountUser accountToUpdate = accountUserFromDb.get();
//
//            if(accountUser.containsKey("firstName")){
//                accountToUpdate.setFirstName((String) accountUser.get("firstName"));
//            }
//            if(accountUser.containsKey("lastName")){
//                accountToUpdate.setFirstName((String) accountUser.get("lastName"));
//            }
//            if(accountUser.containsKey("email")){
//                accountToUpdate.setFirstName((String) accountUser.get("email"));
//            }
//            if(accountUser.containsKey("password")){
//                accountToUpdate.setFirstName((String) accountUser.get("password"));
//            }
//            if(accountUser.containsKey("phoneNumber")){
//                accountToUpdate.setFirstName((String) accountUser.get("phoneNumber"));
//            }
//            AccountUser updatedAccountUser = accountUserRepository.save(accountToUpdate);
//
//            return new ResponseEntity<>(updatedAccountUser, HttpStatus.CREATED);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Remove Account User
    public ResponseEntity<AccountUser> removeAccountUser(int id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
//        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.NO_CONTENT);
    }

}
