package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.student.StudentsResponse;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.repositories.custom.CustomStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepositoryImpl implements CustomStudentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<StudentsResponse> getAllStudents() {
        String query = """
                SELECT
                a.id    AS id,
                CONCAT(u.first_name,' ',u.last_name)    AS full_name,
                g.name     AS group_name,
                a.study_format    AS study_format,
                a.phone_number    AS phone_number,
                a.email       AS email
                FROM users u
                JOIN accounts a ON u.id = a.user_id
                JOIN groups g ON g.id = a.student_group_id
                WHERE a.role = 'STUDENT'
                   """;
        return jdbcTemplate.query(query, (resultSet, i) ->
                StudentsResponse.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .groupName(resultSet.getString("group_name"))
                        .studyFormat(StudyFormat.valueOf(resultSet.getString("study_format")))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .email(resultSet.getString("email"))
                        .build());
    }

    @Override
    public Optional<StudentsResponse> getStudentById(Long id) {
        String query = """
                SELECT
                a.id    AS id,
                CONCAT(u.first_name,' ',u.last_name)    AS full_name,
                g.name     AS group_name,
                a.study_format    AS study_format,
                a.phone_number    AS phone_number,
                a.email       AS email
                FROM users u
                JOIN accounts a ON u.id = a.user_id
                JOIN groups g ON g.id = a.student_group_id
                WHERE a.role = 'STUDENT'
                AND a.id = ?
                   """;
        return jdbcTemplate.query(query, (resultSet, i) ->
                StudentsResponse.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .groupName(resultSet.getString("group_name"))
                        .studyFormat(StudyFormat.valueOf(resultSet.getString("study_format")))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .email(resultSet.getString("email"))
                        .build(), id).stream().findAny();
    }
}
