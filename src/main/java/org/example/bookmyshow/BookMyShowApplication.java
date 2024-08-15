package org.example.bookmyshow;

import org.example.bookmyshow.controllers.UserController;
import org.example.bookmyshow.dtos.SignUpRequestDto;
import org.example.bookmyshow.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("rahul.saini+1@abc.com");
        signUpRequestDto.setPassword("Test@123");
        SignUpResponseDto response = userController.signUp(signUpRequestDto);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

}
