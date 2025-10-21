package com.example.demo;
import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository) {
        return (args) -> {
            System.out.println("\n=== Testing Student Repository ===\n");

            System.out.println("1. Finding all students:");
            List<Student> allStudents = studentRepository.findAll();
            allStudents.forEach(System.out::println);

            System.out.println("\n2. Finding student by code 'STU001':");
            studentRepository.findByCode("STU001")
                    .ifPresent(System.out::println);

            System.out.println("\n3. Finding all first-year students:");
            List<Student> firstYearStudents = studentRepository.findByYear(1);
            firstYearStudents.forEach(System.out::println);

            System.out.println("\n4. Saving a new student:");
            Student newStudent = new Student("STU004", "Alice Williams",
                    "alice.williams@university.edu", 3);
            Student savedStudent = studentRepository.save(newStudent);
            System.out.println("Saved: " + savedStudent);

            System.out.println("\n5. Total number of students: "
                    + studentRepository.count());

            System.out.println("\n6. Searching for students with 'John' in name:");
            List<Student> foundStudents = studentRepository
                    .findByNameContainingIgnoreCase("John");
            foundStudents.forEach(System.out::println);

            System.out.println("\n7. Updating student email:");
            studentRepository.findByCode("STU004").ifPresent(student -> {
                student.setEmail("alice.updated@university.edu");
                Student updated = studentRepository.save(student);
                System.out.println("Updated: " + updated);
            });

            System.out.println("\n8. Deleting student STU004:");
            studentRepository.findByCode("STU004").ifPresent(student -> {
                studentRepository.delete(student);
                System.out.println("Deleted student with code: " + student.getCode());
            });

            System.out.println("\n9. Final count: " + studentRepository.count());
            System.out.println("\n=== Tests Completed ===\n");
        };
    }
}

