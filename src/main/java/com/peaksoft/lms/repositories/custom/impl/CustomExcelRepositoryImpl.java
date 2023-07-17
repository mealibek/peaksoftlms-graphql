package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.excel.ExcelResponse;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.repositories.custom.CustomExcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class CustomExcelRepositoryImpl implements CustomExcelRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ExcelResponse> getAllExportExcelStudents() {
        String sql = """
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


        return jdbcTemplate.query(sql,(resultSet,i)->
                 ExcelResponse.builder().
                       id( resultSet.getLong("id")).
                        fullName(resultSet.getString("full_name")).
                        group(resultSet.getString("group_name")).
                        studyFormat(StudyFormat.valueOf(resultSet.getString("study_format"))).
                        phoneNumber(resultSet.getString("phone_number")).
                        email(resultSet.getString("email")).build());
        }
}
