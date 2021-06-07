package com.melidh.desafiospring;

import com.melidh.desafiospring.domain.User;
import com.melidh.desafiospring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DesafioSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSpringApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Initializing db");

        User c1 = new User(null, "Joao");
        User c2 = new User(null, "Julia");
        User c3 = new User(null, "Jose");
        User c4 = new User(null, "Ana");

        User s1 = new User(null,"Mariano");
        User s2 = new  User(null,"Mafaldaa");
        User s3 = new User(null,"Marcio");

        userRepository.saveAll(Arrays.asList(c1,c2,c3,c4,s1,s2,s3));
    }

}
