package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.repositories.custom.CustomCourseRepository;
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


    @Override
    public Optional<CourseResponse> getById(Long id) {
        String sql = """
                SELECT
                c.id        AS courseID,
                c.name      AS courseName,
                c.description       AS courseDescription,
                c.start_date    AS courseStartDate
                FROM courses c
                WHERE c.id = ?
                """;

        return jdbcTemplate.query(sql,(resultSet, i) ->CourseResponse.builder()
                .id(resultSet.getLong("courseID"))
                .name(resultSet.getString("courseName"))
                .description(resultSet.getString("courseDescription"))
                .startDate(resultSet.getDate("courseStartDate").toLocalDate())
                .build(),id).stream().findAny();
    }
}
