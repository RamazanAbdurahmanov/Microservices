package az.ramazan.springbootrestapi.controllers;

import az.ramazan.springbootrestapi.beans.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "Ramazan", "Abdurahmanov");
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ramazan", "Abdurahmanov"));
        students.add(new Student(2, "Elxan", "Abdurahmanov"));
        students.add(new Student(3, "Royal", "Shixmemmedov"));
        return students;
    }

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }
    @GetMapping("students/query")
    public  Student studentRequestVariable(@RequestParam int id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName){

        return new Student(id,firstName,lastName);
    }

}
