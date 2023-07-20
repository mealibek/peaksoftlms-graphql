package com.peaksoft.lms.models;

import com.peaksoft.lms.enums.Role;
import com.peaksoft.lms.enums.StudyFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Builder
@Getter
@Setter
public class Account implements UserDetails {

    @Id
    @GeneratedValue(generator = "account_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_id_gen", sequenceName = "account_id_seq", allocationSize = 1, initialValue = 8)
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

    @ManyToOne(cascade = {PERSIST, DETACH, MERGE, REFRESH})
    private Course course;

    @JoinTable(name = "instructors_groups")
    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private List<Group> groups;

    @JoinColumn(name = "student_group_id")
    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Group group;
    @OneToMany(cascade = ALL, mappedBy = "account")
    private List<Result> results;

    @PrePersist
    protected void onCreate() {
        modifiedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
