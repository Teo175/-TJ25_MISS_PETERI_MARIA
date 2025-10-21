package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseType type;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 20)
    private String abbr;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    private Pack pack;

    @Column(name = "group_count", nullable = false)
    private Integer groupCount = 1;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Constructors, Getters, Setters, toString()
}

enum CourseType {
    COMPULSORY,
    OPTIONAL
}
