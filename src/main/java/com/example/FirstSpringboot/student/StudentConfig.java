package com.example.FirstSpringboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = Student.builder()
                            .email("johndoe@gmail.com")
                            .birth(LocalDate.of(2000, Month.APRIL, 5))
                            .name("John")
                            .build();

            Student foo = Student.builder()
                    .email("foo@gmail.com")
                    .birth(LocalDate.of(2010, Month.APRIL, 5))
                    .name("Foo")
                    .build();

            repository.saveAll(
                    List.of(john, foo)
            );
        };
    }
}
