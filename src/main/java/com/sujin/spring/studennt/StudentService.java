package com.sujin.spring.studennt;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> stdoptional = studentRepository.findByEmail(student.getEmail());
        if(stdoptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }
}
