package com.example.FirstSpringboot.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}