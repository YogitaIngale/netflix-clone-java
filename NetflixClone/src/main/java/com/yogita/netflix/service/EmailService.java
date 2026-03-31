package com.yogita.netflix.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailService {

    private final JavaMailSender sender;

    public EmailService(JavaMailSender sender){
        this.sender = sender;
    }

    public void sendReceipt(String email, String username, String plan){

        try{

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setTo(email);
            helper.setSubject("Payment Receipt");

            String html =
                    "<h2>Payment Successful</h2>" +
                    "<p>User: "+username+"</p>" +
                    "<p>Plan: "+plan+"</p>" +
                    "<p>Date: "+ LocalDate.now()+"</p>";

            helper.setText(html,true);

            sender.send(message);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}