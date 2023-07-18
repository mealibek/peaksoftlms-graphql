package com.peaksoft.lms.dto.responses.group;

import com.peaksoft.lms.dto.responses.student.GroupStudentsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

  private Long id;
  private String name;
  private List<GroupStudentsResponse> students;
}
