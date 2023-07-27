package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.instructor.InstructorResponse;
import com.peaksoft.lms.enums.Gender;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.repositories.custom.CustomInstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomInstructorRepositoryImpl implements CustomInstructorRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<InstructorResponse> getAllInstructors() {
        String query = """
                SELECT 
                a.id AS id,
                CONCAT(u.first_name,' ',u.last_name) AS full_name,
                a.study_format AS specialization,
                a.phone_number AS phone_number,
                u.gender AS gender,
                a.email AS email,
                a.password AS password
                FROM users u 
                JOIN accounts a ON u.id = a.user_id 
                WHERE a.role = 'INSTRUCTOR' 
                """;

        return jdbcTemplate.query(query, (resultSet, i) ->
                InstructorResponse.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .specialization(StudyFormat.valueOf(resultSet.getString("specialization")))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .gender(Gender.valueOf(resultSet.getString("gender")))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build());
    }

    @Override
    public Optional<InstructorResponse> getInstructorById(Long id) {
        String query = """
                SELECT a.id AS id,
                CONCAT(u.first_name,' ',u.last_name) AS full_name,
                a.study_format AS specialization,
                a.phone_number AS phone_number,
                u.gender AS gender,
                a.email AS email,
                a.password AS password
                FROM users u 
                JOIN accounts a ON u.id = a.user_id 
                WHERE a.role = 'INSTRUCTOR' 
                """;

        return jdbcTemplate.query(query, (resultSet, i) ->
                InstructorResponse.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .specialization(StudyFormat.valueOf(resultSet.getString("specialization")))
                        .phoneNumber(resultSet.getString("phone_number"))
                        .gender(Gender.valueOf(resultSet.getString("gender")))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build()).stream().findAny();
    }
}
