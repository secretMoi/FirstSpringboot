package com.example.FirstSpringboot.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity // définit la classe comme entity
@Table // table dans la bd (par défaut le nom de la classe)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate birth;

    @Transient
    private Integer age;

    // automatiquement override le getter fait par Lombok
    public Integer getAge() {
        return Period.between(this.birth, LocalDate.now()).getYears();
    }
}
