package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;





@Service
public class MessageService {

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository)
    {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    //Create new message
    public Message createNewMessage(Message message)
    {
        Optional<Account> optionalAccount = accountRepository.findById(message.getPosted_by());
        if(optionalAccount.isPresent() && message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255)
        {
            return messageRepository.save(message);
        }else{
            return null;
        }
    }


    //Get all messages.
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    //get Message by id.
    public Message getMessageById(int id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    //delete Message by id and returns how many rows affected
    public int deleteMessageById(int id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            messageRepository.deleteById(id);;
            return 1;
        }else{
            return 0;
        }
    }

    //update message by message ID and returns rows affected
    public int updateMessage(int id, Message replacement){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent() && replacement.getMessage_text().length() > 0 && replacement.getMessage_text().length() <= 255){
            Message message = optionalMessage.get();
            message.setMessage_text(replacement.getMessage_text());
            messageRepository.save(message);
            return 1;
        }
        else{
            return 0;
        }

    }

    //get all messages from user id
    public List<Message> getAllMessagesByUser(int id)
    {
        return messageRepository.findByPostedBy(id);
    }

}
