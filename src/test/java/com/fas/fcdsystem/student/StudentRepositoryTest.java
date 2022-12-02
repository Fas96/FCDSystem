package com.fas.fcdsystem.student;

import com.fas.fcdsystem.Student.Gender;
import com.fas.fcdsystem.Student.Student;
import com.fas.fcdsystem.Student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest ;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void itShoudCheckIfStudentExistByEmail() {
        // given
        Student student = new Student(
                "Fas",
                "firibu.anyass@gmail.com",
                 Gender.MALE
        );

        underTest.save(student);

        // when
        Boolean result = underTest.selectExistsEmail(student.getEmail());

        // then
        assertThat(result).isTrue();
    }

    @Test
    void itShoudCheckIfStudentDoesNotExistByEmail() {
        // given
        String email = "firibu.anyass@gmail.com";

        // when
        Boolean result = underTest.selectExistsEmail(email);

        // then
        assertThat(result).isTrue();

    }
}