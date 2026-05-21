package com.info.settlespot.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendHostCredentials(String toEmail, String fullName, String rawPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to SettleSpot — Your Host Account is Ready");
        message.setText(
                "Dear " + fullName + ",\n\n" +
                        "Your host account has been created on SettleSpot.\n\n" +
                        "Login Credentials:\n" +
                        "Email: " + toEmail + "\n" +
                        "Password: " + rawPassword + "\n\n" +
                        "Please login at http://localhost:3000/login and change your password.\n\n" +
                        "Best regards,\nSettleSpot Team"
        );
        mailSender.send(message);
    }

    public void sendBookingStatusEmail(String toEmail, String tenantName,
                                       String propertyTitle, String status) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Booking Update — SettleSpot");
        message.setText(
                "Dear " + tenantName + ",\n\n" +
                        "Your booking for \"" + propertyTitle + "\" has been " + status + ".\n\n" +
                        "Login to your dashboard for more details.\n\n" +
                        "Best regards,\nSettleSpot Team"
        );
        mailSender.send(message);
    }
}