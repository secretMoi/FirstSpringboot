package com.example.FirstSpringboot.student;

import com.example.FirstSpringboot.exceptions.EmailTakenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // initialise les mocks dans cette classe (studentRepository)
class StudentServiceTest {
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        // when
        studentService.getStudents();

        // then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        // given
        Student student = Student.builder()
                .name("Goku")
                .email("goku@mail.com")
                .birth(LocalDate.of(2000, 1, 1))
                .build();

        // when
        studentService.addNewStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class); // définit un ArgumentCaptor pour la classe Student
        verify(studentRepository).save(studentArgumentCaptor.capture()); // vérifie que addNewStudent a bien sauvé un student

        Student capturedStudent = studentArgumentCaptor.getValue(); // récupère le student sauvé par addNewStudent
        assertThat(capturedStudent).isEqualTo(student); // vérifie que le student sauvé par addNewStudent est bien le bon
    }

    @Test
    void thrownWhenEmailIsTaken() {
        // given
        Student student = Student.builder()
                .name("Goku")
                .email("foo@gmail.com")
                .birth(LocalDate.of(2000, 1, 1))
                .build();

        // force la condition pour lever l'exception
//        given(studentRepository.findStudentByEmail(student.getEmail()))
//                .willReturn(java.util.Optional.of(student));
        given(studentRepository.findStudentByEmail(anyString()))
                .willReturn(java.util.Optional.of(student));

        // when
        // then
        // essaye d'ajouter un student et catch l'exception
        assertThatThrownBy(() -> studentService.addNewStudent(student))
                .isInstanceOf(EmailTakenException.class);

        // vérifie que l'on ne sauve aucun student après l'exception
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}