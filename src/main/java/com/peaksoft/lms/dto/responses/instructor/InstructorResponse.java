package com.peaksoft.lms.dto.responses.instructor;

import com.peaksoft.lms.enums.Gender;
import com.peaksoft.lms.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
  private Long id;
  private String fullName;
  private StudyFormat specialization;
  private String phoneNumber;
  private Gender gender;
  private String email;
  private String password;
}
