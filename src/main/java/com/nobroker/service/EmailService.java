package com.nobroker.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import static com.nobroker.service.EmailVerificationService.emailOtpMapping;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final UserService userService;
    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }
    public String generateOtp(){
        return String.format("%06d", new java.util.Random().nextInt(100000));
    }
    public Map<String, String> sendOtpEmail(String email){
        String otp=generateOtp();
        //Save the otp for later verification
        emailOtpMapping.put(email, otp);
        sendEmail(email,"OTP for Email Verification", "Your OTP is: "+otp);
        Map<String, String> response = new HashMap<>();
        response.put("status", "sucsess");
        response.put("message", "OTP send to successfully");
        return response;
    }
    private void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rlbalu1432@gamil.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
