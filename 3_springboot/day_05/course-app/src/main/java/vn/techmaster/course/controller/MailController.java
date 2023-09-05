package vn.techmaster.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    public final JavaMailSender emailSender;

    public MailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/send-mail-simple")
    public ResponseEntity<?> sendMailSimple() {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("abc@gmail.com");
        message.setSubject("Bùi Hiên send mail");
        message.setText("Hello World");

        // Send Message!
        emailSender.send(message);

        return ResponseEntity.ok("Send mail success");
    }
}
