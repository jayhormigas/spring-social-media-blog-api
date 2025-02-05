package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the Account entity.
 * This interface extends JpaRepository, which provides built-in methods for database interaction.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Retrieves an account based on the provided username.
     * 
     * @param username The username of the account to retrieve.
     * @return The account with the specified username, or null if no such account exists.
     */
    Account findByUsername(String username);

    /**
     * Retrieves an account based on the provided username and password.
     * 
     * @param username The username of the account.
     * @param password The password associated with the account.
     * @return An Optional containing the account if a match is found, or an empty Optional if no match exists.
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);

    /**
     * Retrieves an account based on the provided account ID.
     * 
     * @param accountId The ID of the account to retrieve.
     * @return An Optional containing the account if found, or an empty Optional if no account exists with the given ID.
     */
    Optional<Account> findByAccountId(Integer accountId);
}
