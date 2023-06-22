package com.peaksoft.lms.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tests")
@Builder
public class Test {
    @Id
    @GeneratedValue(generator = "test_id_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "test_id_gen",sequenceName = "test_id_seq",allocationSize = 1,initialValue = 5)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "test")
    private List<Question> questions;
    @OneToOne(cascade = {PERSIST,MERGE,DETACH,REFRESH},mappedBy = "test")
    private Lesson lesson;
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
