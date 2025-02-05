package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * MessageService provides business logic for handling messages.
 * It includes methods for message validation, retrieval, creation, updating, and deletion.
 */

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Validates and creates a new message.
     * 
     * @param message The message object containing the message text and associated user ID.
     * @return The saved message if validation is successful, otherwise returns null.
     */
    public Message verifyMessage(Message message) {
        // Validate message is not blank and does not exceed 255 characters
        if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() >= 255) {
            return null;
        }

        // Validate that the postedBy user exists in the database
        if (message.getPostedBy() == null || accountRepository.findByAccountId(message.getPostedBy()).isEmpty()) {
            return null;
        }

        // Save and return the new message
        return messageRepository.save(message);
    }

    /**
     * Retrieves all messages from the database.
     * 
     * @return A list of all messages. Returns an empty list if no messages exist.
     */
    public List<Message> retrieveMessages() {
        return messageRepository.findAll();
    }

    /**
     * Retrieves a message by its ID.
     * 
     * @param message_id The ID of the message to retrieve.
     * @return An Optional containing the message if found, otherwise an empty Optional.
     */
    public Optional<Message> getMessageById(Integer message_id) {
        return messageRepository.findById(message_id);
    }

    /**
     * Deletes a message by its ID.
     * 
     * @param message_id The ID of the message to delete.
     * @return 1 if the message was successfully deleted, 0 if the message was not found.
     */
    public int deleteMessageById(Integer message_id) {
        if (messageRepository.existsById(message_id)) {
            messageRepository.deleteById(message_id);
            return 1; // One row was deleted
        }

        return 0; // No row deleted
    }

    /**
     * Updates the text of an existing message.
     * 
     * @param message_id The ID of the message to update.
     * @param newMessageText The new message text to update.
     * @return 1 if the update was successful, 0 if the message does not exist or validation fails.
     */
    public int updateMessageById(Integer message_id, String newMessageText) {

        // Validate that the message ID exists
        Optional<Message> messageToUpdate = messageRepository.findById(message_id);
        if (messageToUpdate.isEmpty()) {
            return 0; // No row updated
        }

        // Validate that the new message_text is not blank and not over 255 characters
        if (newMessageText == null || newMessageText.isBlank() || newMessageText.length() >= 255) {
            return 0; // No row updated due to invalid text
        }

        // Retrieve existing message and update it
        Message existingMessage = messageToUpdate.get();
        existingMessage.setMessageText(newMessageText);
        messageRepository.save(existingMessage); // Save updated message
        return 1;
    }

    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param account_id The ID of the user whose messages should be retrieved.
     * @return A list of messages posted by the specified user. Returns an empty list if the user has no messages.
     */
    public List<Message> retrieveMessagesFromUser(Integer account_id) {
        return messageRepository.findByPostedBy(account_id);
    }      
}
