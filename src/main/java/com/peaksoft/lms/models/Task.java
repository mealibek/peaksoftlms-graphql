package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Builder
public class Task {

  @Id
  @GeneratedValue(generator = "task_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "task_id_gen", sequenceName = "task_id_seq", allocationSize = 1, initialValue = 5)
  private Long id;
  private String name;
  private String description;
  @OneToOne(cascade = ALL)
  private File file;
  @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
  private Lesson lesson;
  private LocalDate deadLine;
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
