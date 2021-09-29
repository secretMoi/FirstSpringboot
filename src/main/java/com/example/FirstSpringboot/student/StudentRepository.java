package com.example.FirstSpringboot.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // SELECT * FROM student WHERE email = ? -> équivalent SQL des 2 lignes suivantes
    // @Query("SELECT s FROM Student s WHERE s.email = ?1") -> grâce à l'annotaion @Entity dans le model Student
    Optional<Student> findStudentByEmail(String email);
}
