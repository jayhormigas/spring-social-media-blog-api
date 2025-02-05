package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Message entity.
 * This interface extends JpaRepository, which provides built-in methods for database interaction.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Retrieves a list of messages posted by a specific user.
     * 
     * @param postedBy The ID of the user whose messages are to be retrieved.
     * @return A list of messages associated with the specified user. Returns an empty list if no messages exist.
     */
    List<Message> findByPostedBy(Integer postedBy);
}
