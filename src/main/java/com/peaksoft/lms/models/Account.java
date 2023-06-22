package com.peaksoft.lms.models;

import com.peaksoft.lms.enums.Role;
import com.peaksoft.lms.enums.StudyFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Builder
public class Account {
    @Id
    @GeneratedValue(generator = "account_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_id_gen", sequenceName = "account_id_seq", allocationSize = 1,initialValue = 8)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String resetPasswordToken;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @ManyToOne(cascade = {PERSIST,DETACH,MERGE,REFRESH})
    private Course course;

    @JoinTable(name = "instructors_groups")
    @ManyToMany(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private List<Group> groups;

    @JoinColumn(name = "student_group_id")
    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH})
    private Group group;

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
