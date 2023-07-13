package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.course.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<CourseResponse> getAll() {
        String sql = """
                SELECT
                c.id            AS courseID,
                c.name          AS courseName,
                c.description   AS courseDescription,
                c.start_date    AS courseStartDate
                FROM courses c
                """;

        return jdbcTemplate.query(sql, (resultSet, i) -> CourseResponse.builder()
                .id(resultSet.getLong("courseID"))
                .name(resultSet.getString("courseName"))
                .description(resultSet.getString("courseDescription"))
                .startDate(resultSet.getDate("courseStartDate").toLocalDate())
                .build());
    }
}
