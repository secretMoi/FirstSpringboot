package com.example.FirstSpringboot.student;

import com.example.FirstSpringboot.exceptions.EmailTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
            throw new EmailTakenException(student.getEmail());

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.existsById(studentId))
            studentRepository.deleteById(studentId);
        else
            throw new IllegalStateException("Student with id " + studentId + " does not exists");
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id " + studentId + " does not exists")
        );

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
                throw new IllegalStateException("Email taken");

            student.setEmail(email);
        }
    }
}
