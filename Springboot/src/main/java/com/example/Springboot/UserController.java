package com.example.Springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private JavaMailSender javaMailSender;

    // Create or Update a User
    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        AppUser savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get All Users
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a User by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        Optional<AppUser> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sendmail")
    public ResponseEntity<Object> sendEmail(@RequestBody List<AppUser> user) {

        try {
            for (AppUser users : user) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(users.getEmail());
                message.setSubject("Application for Java Developer Position");
                message.setText("Dear Recruiter,\n" +
                        "\n" +
                        "I hope this email finds you well.\n" +
                        "\n" +
                        "My name is Rakesh Nagunoori. I am a Java Developer with one year of experience at Yatra Online Private Limited where I contributed to application development and bug resolution using Java. I am interested in the Java Developer position at your esteemed company with strong expertise in Java, Spring ,Spring Boot, Hibernate, Spring MVC, MySQL, Elasticsearch ,Kibana, Jenkins, Kubernetes, CI/CD pipelines, Docker, Akamai server, REST API creation, Grafana, web services and MongoDB .I am confident I can be a valuable addition to your team.\n" +
                        " \n" +
                        "I am available to start immediately and am eager to contribute my skills and experience to your organization.\n" +
                        "\n" +
                        "Attached is my resume for your review. I would appreciate the opportunity to discuss how my background aligns with your team's needs. Please feel free to reach out if you need any additional information.\n" +
                        "\n" +
                        "Thank you for considering my application. I look forward to the possibility of joining your team.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Yours sincerely,\n" +
                        "Rakesh Nagunoori\n" +
                        "nagunoorirakesh2407@gmail.com\n" +
                        "9700731821 \n");
                javaMailSender.send(message);
            }

            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while sending email: " + e.getMessage());
        }
    }
}