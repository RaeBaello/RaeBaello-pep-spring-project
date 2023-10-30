package com.example.repository;

import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    //find all messages by account id.
    //List<Message> findMessagesByPostedBy(int posted_by);
    @Query("Select m from Message m where m.posted_by = ?1")
    List<Message> findByPostedBy(Integer posted_by);
}
