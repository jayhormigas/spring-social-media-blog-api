package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class that provides business logic for managing user accounts.
 * This class handles account registration and authentication.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Registers a new account after validating the username and password.
     * 
     * @param account The account object containing username and password details.
     * @return The newly created account if registration is successful, or null if validation fails.
     * @throws IllegalArgumentException if the username already exists in the system.
     */
    public Account registerAccount(Account account) {
        // Validate username is not blank
        if (account.getUsername() == null || account.getUsername().isBlank()) {
            return null; // Return null if username is invalid
        }

        // Validate password is at least 4 characters long
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null; // Return null if password is too short
        }

        // Check if the username is already taken
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Save and return the newly registered account
        return accountRepository.save(account);
    }

    /**
     * Verifies user login by checking if the provided credentials match an existing account.
     * 
     * @param account The account object containing the login credentials (username and password).
     * @return The corresponding account if login is successful, or null if credentials are invalid.
     */
    public Account verifyLogin(Account account) {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());

        // Return the account if found, otherwise return null
        return optionalAccount.orElse(null);
    }
}
