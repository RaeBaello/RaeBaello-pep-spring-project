package com.example.controller;

import java.util.List;

import javax.swing.RepaintManager;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.AccountService;

import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    @Autowired
    MessageService messageService;

    @Autowired
    AccountService accountService;

    //New User Registration POST
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account newAccount)
    {
        int response = accountService.checkNewUser(newAccount);
        //0 means complete, 1 means duplicate username, 2 means other conditions not met
        if(response == 0)
        {
            Account account = accountService.registerNewUser(newAccount);
            return ResponseEntity.status(200).body(account);
        }
        else if(response == 1)
        {
            return ResponseEntity.status(409).body(null);
        }
        else
        {
            return ResponseEntity.status(400).body(null);
        }
    }

    //Login POST
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> loginAccount(@RequestBody Account verifyAccount)
    {
        Account account = accountService.verifyLogin(verifyAccount);
        if(account != null)
        {
            return ResponseEntity.status(200).body(account);
        }
        else
        {
            return ResponseEntity.status(401).body(account);
        }


    }

    //Create New Message POST
    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> registerNewMessage(@RequestBody Message newMessage)
    {
        Message message = messageService.createNewMessage(newMessage);
        if(message != null)
        {
            return ResponseEntity.status(200).body(message);
        }
        else{
            return ResponseEntity.status(400).body(message);
        }

    }


    //Get all messages GET
    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> showAllMessages()
    {
        List<Message> list = messageService.getAllMessages();
        return ResponseEntity.status(200).body(list);
    }

    //Get message by ID GET
    @GetMapping("messages/{message_id}")
    public @ResponseBody ResponseEntity<Message> showMessageById(@PathVariable int message_id)
    {
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
    }

    //Delete message by ID DELETE
    @DeleteMapping("messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> deleteMessageById(@PathVariable int message_id)
    {
        int rows = messageService.deleteMessageById(message_id);
        if(rows == 1)
        {
            return ResponseEntity.status(200).body(rows);
        }
        else{
            return ResponseEntity.status(200).body(null);
        }
    }

    //Update message by Id
    @PatchMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> updateMessageById(@RequestBody Message updatedMessage, @PathVariable int message_id)
    {
        int rows = messageService.updateMessage(message_id, updatedMessage);
        if(rows == 1)
        {
            return ResponseEntity.status(200).body(rows);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }

    //Get all messages by user using account id GET
    @GetMapping("accounts/{account_id}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable int account_id)
    {
        List<Message> list = messageService.getAllMessagesByUser(account_id);
        return ResponseEntity.status(200).body(list);
    }




    
}
