package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.dto.responses.instructor.InstructorResponse;
import com.peaksoft.lms.dto.responses.student.StudentsResponse;
import com.peaksoft.lms.enums.Role;
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
        u.id            AS uId,
        a.role          AS role,
        a.email         AS email,
        a.phone_number  AS phoneNumber,
        a.password      AS password,
        a.study_format  AS studyFormat,
        g.name          AS groupName,
        CONCAT(u.last_name,' ',u.first_name) AS fullName
        FROM courses c
        LEFT JOIN files f on c.file_id = f.id
        LEFT JOIN accounts a ON c.id = a.course_id
        LEFT JOIN groups g ON a.student_group_id = g.id
        LEFT JOIN users u ON a.user_id = u.id
        WHERE c.id = ?
        """;

    List<CourseResponse> results = jdbcTemplate.query(sql, (resultSet, i) -> {
      CourseResponse courseResponse = new CourseResponse();
      courseResponse.setId(resultSet.getLong("courseID"));
      courseResponse.setName(resultSet.getString("courseName"));
      courseResponse.setDescription(resultSet.getString("courseDescription"));
      courseResponse.setStartDate(resultSet.getDate("courseStartDate").toLocalDate());
      courseResponse.setImageUrl(resultSet.getString("fileUrl"));

      List<StudentsResponse> studentResponses = new ArrayList<>();
      List<InstructorResponse> instructorResponses = new ArrayList<>();

      do {
        if (resultSet.getObject("uId") != null) {
          Long uId = resultSet.getLong("uId");
          String role = resultSet.getString("role");
          String fullName = resultSet.getString("fullName");
          String phoneNumber = resultSet.getString("phoneNumber");
          String email = resultSet.getString("email");
          String password = resultSet.getString("password");
          String studyFormat = resultSet.getString("studyFormat");

          if (Role.valueOf(role).equals(Role.STUDENT)) {
            StudentsResponse response = new StudentsResponse();
            response.setId(uId);
            response.setFullName(fullName);
            response.setGroupName(resultSet.getString("groupName"));
            response.setStudyFormat(StudyFormat.valueOf(studyFormat));
            response.setPhoneNumber(phoneNumber);
            response.setEmail(email);
            response.setPassword(password);
            studentResponses.add(response);
          } else if (Role.valueOf(role).equals(Role.INSTRUCTOR)) {
            InstructorResponse response = new InstructorResponse();
            response.setId(uId);
            response.setFullName(fullName);
            response.setSpecialization(StudyFormat.valueOf(studyFormat));
            response.setPhoneNumber(phoneNumber);
            response.setEmail(email);
            response.setPassword(password);
            instructorResponses.add(response);
          }
        }
      } while (resultSet.next());

      courseResponse.setStudents(studentResponses);
      courseResponse.setInstructors(instructorResponses);

      return courseResponse;
    }, id);

    return results.stream().findFirst();
  }

}
