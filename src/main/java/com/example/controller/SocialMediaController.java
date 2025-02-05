package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

/**
 * SocialMediaController handles all API endpoints related to user accounts and messages.
 * This controller provides functionalities for user registration, login, message creation, retrieval,
 * deletion, and updates using RESTful principles.
 */

 @RestController
 public class SocialMediaController {
     @Autowired
     private AccountService accountService;
 
     @Autowired
     private MessageService messageService;
 
     /**
      * Handles user registration.
      * 
      * @param account The account details from the request body.
      * @return ResponseEntity containing the created account (with account ID) if successful,
      *         or an error status if registration fails (400 for bad input, 409 for duplicate username).
      */
     @PostMapping("/register")
     public ResponseEntity<?> registerUser(@RequestBody Account account) {
         try {
             Account newAccount = accountService.registerAccount(account);
             if (newAccount != null) {
                 return ResponseEntity.status(200).body(newAccount); // 200 OK
             }
             return ResponseEntity.status(400).body("Registration Unsuccessful"); // 400 Client Error
         } catch (IllegalArgumentException e) {
             return ResponseEntity.status(409).body(e.getMessage()); // 409 Conflict
         }
     }
 
     /**
      * Handles user login.
      * 
      * @param account The login credentials from the request body (username and password).
      * @return ResponseEntity containing the authenticated account if login is successful,
      *         or an error status (401 Unauthorized) if login fails.
      */
     @PostMapping("/login")
     public ResponseEntity<?> loginUser(@RequestBody Account account) {
         Account newAccount = accountService.verifyLogin(account);
 
         if (newAccount != null) {
             return ResponseEntity.status(200).body(newAccount); // 200 OK
         }
 
         return ResponseEntity.status(401).body("Login Unsuccessful"); // 401 Unauthorized
     }
 
     /**
      * Handles message creation.
      * 
      * @param message The message details from the request body.
      * @return ResponseEntity containing the created message (with message ID) if successful,
      *         or an error status (400 Client Error) if validation fails.
      */
     @PostMapping("/messages")
     public ResponseEntity<?> createMessage(@RequestBody Message message) {
         Message newMessage = messageService.verifyMessage(message);
 
         if (newMessage != null) {
             return ResponseEntity.status(200).body(newMessage); // 200 OK
         }
 
         return ResponseEntity.status(400).body("Message Creation Unsuccessful"); // 400 Client Error
     }
 
     /**
      * Retrieves all messages from the database.
      * 
      * @return ResponseEntity containing a list of all messages. Returns an empty list if no messages exist.
      */
     @GetMapping("/messages")
     public ResponseEntity<List<Message>> getAllMessages() {
         List<Message> messages = messageService.retrieveMessages();
 
         return ResponseEntity.status(200).body(messages); // 200 OK
     }
 
     /**
      * Retrieves a specific message by its ID.
      * 
      * @param message_id The ID of the message to retrieve.
      * @return ResponseEntity containing the message if found, or an empty body if the message does not exist.
      */
     @GetMapping("/messages/{message_id}")
     public ResponseEntity<?> getMessage(@PathVariable Integer message_id) {
         Optional<Message> message = messageService.getMessageById(message_id);
 
         if (message.isEmpty()) {
             return ResponseEntity.status(200).body(null); // 200 OK, Empty body
         }
 
         return ResponseEntity.status(200).body(message); // 200 OK
     }
 
     /**
      * Deletes a message by its ID.
      * 
      * @param message_id The ID of the message to delete.
      * @return ResponseEntity containing the number of rows deleted (1 if successful, 0 if not found).
      */
     @DeleteMapping("/messages/{message_id}")
     public ResponseEntity<?> deleteMessage(@PathVariable Integer message_id) {
         int rowsDeleted = messageService.deleteMessageById(message_id);
 
         if (rowsDeleted > 0) {
             return ResponseEntity.status(200).body(rowsDeleted); // 200 OK
         }
 
         // Message did not exist, nothing deleted
         return ResponseEntity.status(200).body(null); // 200 OK, Empty body
     }
 
     /**
      * Updates the text of a message by its ID.
      * 
      * @param message_id The ID of the message to update.
      * @param message The new message text in the request body.
      * @return ResponseEntity containing the number of rows updated (1 if successful, 0 if validation fails).
      */
     @PatchMapping("/messages/{message_id}")
     public ResponseEntity<?> updateMessage(@PathVariable Integer message_id, @RequestBody Message message) {
         int rowsUpdated = messageService.updateMessageById(message_id, message.getMessageText());
 
         if (rowsUpdated > 0) {
             return ResponseEntity.status(200).body(rowsUpdated); // 200 OK
         }
 
         return ResponseEntity.status(400).body("Message Update Unsuccessful"); // 400 Client Error
     }
 
     /**
      * Retrieves all messages posted by a specific user.
      * 
      * @param account_id The ID of the user whose messages should be retrieved.
      * @return ResponseEntity containing a list of messages by the user, or an empty list if none exist.
      */
     @GetMapping("/accounts/{account_id}/messages")
     public ResponseEntity<?> getAllMessagesFromUser(@PathVariable Integer account_id) {
         List<Message> messages = messageService.retrieveMessagesFromUser(account_id);
         
         return ResponseEntity.status(200).body(messages); // 200 OK
     }    
 }