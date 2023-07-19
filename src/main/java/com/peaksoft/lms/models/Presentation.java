package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "presentations")
@Builder
public class Presentation {

  @Id
  @GeneratedValue(generator = "presentation_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "presentation_id_gen", sequenceName = "presentation_id_seq", allocationSize = 1, initialValue = 5)
  private Long id;
  private String name;
  private String description;
  private String formatPPT;
  @ManyToOne(cascade = {PERSIST, REFRESH, MERGE, DETACH})
  private Lesson lesson;
}
