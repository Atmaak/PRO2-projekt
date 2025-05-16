package cz.uhk.pro2_a;

import cz.uhk.pro2_a.model.User;
import cz.uhk.pro2_a.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Pro2AApplication {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Pro2AApplication(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            addUser("User", "user", "user", "USER");
            addUser("Admin", "admin", "admin", "ADMIN");
        };
    }

    private void addUser(String name, String username, String password, String role){
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userService.saveUser(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(Pro2AApplication.class, args);
    }

}
