package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "options")
@Builder
public class Option {

  @Id
  @GeneratedValue(generator = "option_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "option_id_gen", sequenceName = "option_id_seq", allocationSize = 1, initialValue = 17)
  private Long id;
  private String name;
  private Boolean isCorrect;
  @ManyToOne(cascade = {PERSIST, DETACH, MERGE, REFRESH})
  private Question question;
}
