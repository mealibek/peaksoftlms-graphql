package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courses")
@Builder
public class Course {
    @Id
    @GeneratedValue(generator = "course_id_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_id_gen",sequenceName = "course_id_seq",allocationSize = 1,initialValue = 3)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    @OneToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH},mappedBy = "course")
    private List<Account> instructors;
    @OneToMany(cascade = {PERSIST,DETACH,MERGE,REFRESH},mappedBy = "course")
    private List<Group> groups;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        modifiedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
