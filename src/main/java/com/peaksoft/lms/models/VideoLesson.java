package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video_lessons")
@Builder
public class VideoLesson {

  @Id
  @GeneratedValue(generator = "video_lesson_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "video_lesson_id_gen", sequenceName = "video_lesson_id_seq", allocationSize = 1)
  private Long id;
  private String name;
  private String description;
  @OneToOne(cascade = CascadeType.ALL)
  private File file;
  @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
  private Lesson lesson;
}
