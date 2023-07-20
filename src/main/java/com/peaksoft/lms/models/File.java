package com.peaksoft.lms.models;

import com.peaksoft.lms.enums.FileType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "files")
@Builder
public class File {
    @Id
    @GeneratedValue(generator = "file_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "file_id_gen", sequenceName = "file_id_seq", allocationSize = 1, initialValue = 11)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @OneToOne(mappedBy = "file", cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Group group;
    @OneToOne(mappedBy = "file", cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Course course;
    @OneToOne(mappedBy = "file", cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Task task;
}
