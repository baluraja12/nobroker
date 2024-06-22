package com.nobroker.controller;

import com.nobroker.entity.User;
import com.nobroker.service.EmailService;
import com.nobroker.service.EmailVerificationService;
import com.nobroker.service.ExcelExportService;
import com.nobroker.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private ExcelExportService excelExportService;
    private UserService userService;
    private EmailService emailService;
    private EmailVerificationService emailVerificationService;
    public RegistrationController(UserService userService, EmailService emailService, EmailVerificationService emailVerificationService, ExcelExportService excelExportService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
        this.excelExportService = excelExportService;
    }

    @PostMapping("/registration")
    public Map<String, String> registerUser(@RequestBody User user){
        // Register the user without email verification
        User registerUser = userService.registrationUser(user);
        return emailService.sendOtpEmail(user.getEmail());
    }
    //http://localhost:8080/api/verify-otp?email=&otp
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtp(email, otp);
    }

    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users.xls";
        response.setHeader(headerKey, headerValue);
        excelExportService.generateExcel(response);
    }
}
