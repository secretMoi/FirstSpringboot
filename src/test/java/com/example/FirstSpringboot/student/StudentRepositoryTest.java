package com.example.FirstSpringboot.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager; // permet de tester les méthodes générées par JPA

    @BeforeEach
    void setUp() {
        Student student = Student.builder()
                .name("Goku")
                .birth(LocalDate.now())
                .email("goku@mail.com")
                .build();

        entityManager.persist(student);
    }

    @Test
    public void findById_returnStudent(){
        if(studentRepository.findById(1L).isPresent())
        {
            Student student = studentRepository.findById(1L).get();
            System.out.println(student.getEmail());
            assertEquals(student.getEmail(), "goku@mail.com");
        }
    }
}