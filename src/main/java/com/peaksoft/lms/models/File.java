package com.peaksoft.lms.models;

import com.peaksoft.lms.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
@Builder
public class File {
    @Id
    @GeneratedValue(generator = "file_id_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "file_id_gen",sequenceName = "file_id_seq",allocationSize = 1)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @OneToOne(mappedBy = "file",cascade = {PERSIST,MERGE,DETACH,REFRESH})
    private Group group;
    @OneToOne(mappedBy = "file",cascade = {PERSIST,MERGE,DETACH,REFRESH})
    private Task task;
}
