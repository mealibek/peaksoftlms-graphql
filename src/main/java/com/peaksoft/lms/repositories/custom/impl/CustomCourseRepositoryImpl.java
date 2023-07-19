package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.dto.responses.student.StudentsResponse;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.repositories.custom.CustomCourseRepository;
import java.util.ArrayList;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomCourseRepositoryImpl implements CustomCourseRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<CourseResponse> getAll() {
    String sql = """
        SELECT
        c.id            AS courseID,
        c.name          AS courseName,
        c.description   AS courseDescription,
        c.start_date    AS courseStartDate,
        f.url           AS fileUrl
        FROM courses c
        JOIN files f on c.file_id = f.id
        """;

    return jdbcTemplate.query(sql, (resultSet, i) -> CourseResponse.builder()
        .id(resultSet.getLong("courseID"))
        .name(resultSet.getString("courseName"))
        .description(resultSet.getString("courseDescription"))
        .startDate(resultSet.getDate("courseStartDate").toLocalDate())
        .imageUrl(resultSet.getString("fileUrl"))
        .build());
  }


  @Override
  public Optional<CourseResponse> getById(Long id) {
    String sql = """
        SELECT
        c.id        AS courseID,
        c.name      AS courseName,
        c.description       AS courseDescription,
        c.start_date    AS courseStartDate,
        f.url           AS fileUrl,
        s.id            AS sId,
        a.email         AS sEmail,
        a.phone_number  AS sPhoneNumber,
        a.study_format  AS sStudyFormat,
        g.name          AS groupName,
        CONCAT(s.last_name,' ',s.first_name) AS sFullName
        FROM courses c
        LEFT JOIN files f on c.file_id = f.id
        LEFT JOIN accounts a ON c.id = a.student_group_id
        LEFT JOIN groups g ON a.student_group_id = g.id
        LEFT JOIN users s ON a.user_id = s.id
        WHERE c.id = ?
        """;

    return jdbcTemplate.query(sql, (resultSet, i) -> {
      CourseResponse courseResponse = new CourseResponse();
      courseResponse.setId(resultSet.getLong("courseID"));
      courseResponse.setName(resultSet.getString("courseName"));
      courseResponse.setDescription(resultSet.getString("courseDescription"));
      courseResponse.setStartDate(resultSet.getDate("courseStartDate").toLocalDate());
      courseResponse.setImageUrl(resultSet.getString("fileUrl"));

      if (resultSet.getObject("sId") != null){
        StudentsResponse studentsResponse = new StudentsResponse();
        studentsResponse.setId(resultSet.getLong("sId"));
        studentsResponse.setFullName(resultSet.getString("sFullName"));
        studentsResponse.setGroupName(resultSet.getString("groupName"));
        studentsResponse.setStudyFormat(StudyFormat.valueOf(resultSet.getString("sStudyFormat")));
        studentsResponse.setPhoneNumber(resultSet.getString("sPhoneNumber"));
        studentsResponse.setEmail(resultSet.getString("sEmail"));
        // ? setting students to course
        courseResponse.setStudents(Collections.singletonList(studentsResponse));
        // otherwise it's empty list
      } else courseResponse.setStudents(new ArrayList<>());
      return courseResponse;
    },id).stream().findAny();
  }
}
