package com.monkdevs.studentservice.service;

import com.monkdevs.studentservice.dto.School;
import com.monkdevs.studentservice.dto.StudentResponse;
import com.monkdevs.studentservice.entity.Student;
import com.monkdevs.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> createStudent(Student student)
    {
        try
        {
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(int id)
    {
        Optional<Student> student =  studentRepository.findById(id);

        if(student.isPresent())
        {
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/school/" + student.get().getSchoolId(), School.class);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents()
    {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty())
        {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>("No Students",HttpStatus.NOT_FOUND);
        }
    }
}
