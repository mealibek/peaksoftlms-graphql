package com.peaksoft.lms.models;

import com.peaksoft.lms.enums.OptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
@Builder
public class Question {

  @Id
  @GeneratedValue(generator = "question_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "question_id_gen", sequenceName = "question_id_seq", allocationSize = 1, initialValue = 9)
  private Long id;
  private Integer questionOrder;
  private String name;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
  private List<Option> options;
  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
  private Test test;
  @Enumerated(EnumType.STRING)
  private OptionType optionType;
}
