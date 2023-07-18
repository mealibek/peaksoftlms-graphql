package com.peaksoft.lms.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
@Builder
public class Lesson {

  @Id
  @GeneratedValue(generator = "lesson_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "lesson_id_gen", sequenceName = "lesson_id_seq", allocationSize = 1, initialValue = 5)
  private Long id;
  private String name;
  @ManyToOne(cascade = {PERSIST, REFRESH, DETACH, MERGE})
  private Group group;
  @OneToMany(mappedBy = "lesson", cascade = ALL)
  private List<VideoLesson> videoLesson;
  @OneToMany(cascade = ALL, mappedBy = "lesson")
  private List<Presentation> presentations;
  @OneToMany(cascade = ALL, mappedBy = "lesson")
  private List<Task> tasks;
  @OneToOne(cascade = ALL)
  private Test test;
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
