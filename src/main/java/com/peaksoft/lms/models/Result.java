package com.peaksoft.lms.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "results")
@Builder
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(generator = "result_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "result_id_gen", sequenceName = "result_id_seq", allocationSize = 1, initialValue = 5)
    private Long id;
    private Integer countCorrect;
    private Integer countInCorrect;
    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Account account;
    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Test test;
}
