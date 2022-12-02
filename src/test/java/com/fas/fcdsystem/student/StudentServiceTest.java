package com.fas.fcdsystem.student;

import com.fas.fcdsystem.Student.Gender;
import com.fas.fcdsystem.Student.Student;
import com.fas.fcdsystem.Student.StudentRepository;
import com.fas.fcdsystem.Student.StudentService;
import com.fas.fcdsystem.exception.BadRequestException;
import com.fas.fcdsystem.exception.StudentNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

   private StudentService underTest;
   @Mock
   private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
//         autoCloseable= MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }



    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        // given
        Student student = new Student(
                "Fas",
                "firibu.anyass@gmail.com",
                Gender.MALE
        );

        //when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        // assert
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        Student student = new Student(
                "Fas",
                "firibu.anyass@gmail.com",
                Gender.MALE
        );

        //when
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        // then we can assert
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " +student.getEmail()+ " taken");

        //you can use anyString() to replace the email

        verify(studentRepository, never()).save(any());

    }


    @Test
    void canDeleteStudent() {
        //save student
        Student student = new Student(
                "Fas",
                "firibu.anyass@gmail.com",
                Gender.MALE
        );


        given(studentRepository.existsById(student.getId())).willReturn(true);

        //when
        underTest.deleteStudent(student.getId());

        //then
        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    void willThrowWhenDeleteStudent() {
        //save student
        Student student = new Student(
                "Fas",
                "firibu.anyass@gmail.com",
                Gender.MALE
        );

        //when
        given(studentRepository.existsById(student.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteStudent(student.getId()))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exists");
    }
}