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
@Table(name = "groups")
@Builder
public class Group {
    @Id
    @GeneratedValue(generator = "group_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_id_gen", sequenceName = "group_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    @OneToOne(cascade = ALL)
    private File file;
    @OneToMany(cascade = ALL,mappedBy = "group")
    private List<Account> students;
    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH},mappedBy = "groups")
    private List<Account> instructors;
    @ManyToOne(cascade = {PERSIST, REFRESH, MERGE, DETACH})
    private Course course;
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
