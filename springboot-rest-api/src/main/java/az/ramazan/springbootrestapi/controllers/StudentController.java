package az.ramazan.springbootrestapi.controllers;

import az.ramazan.springbootrestapi.beans.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Rest API that Handles HTTP PUT Request-updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;

    }

}
