package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.dto.responses.student.StudentsResponse;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.exceptions.BadRequestException;
import com.peaksoft.lms.repositories.custom.CustomGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomGroupRepositoryImpl implements CustomGroupRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupsResponse> getAll() {
        String query = """
                SELECT
                g.id AS id,
                g.name AS name,
                g.description AS description,
                f.url AS image,
                g.start_date AS start_date,
                g.finish_date AS finish_date
                FROM groups g
                LEFT JOIN files f ON g.file_id = f.id
                """;

        return jdbcTemplate.query(query, (resultSet, i) ->
                GroupsResponse.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getString("image"))
                        .startDate(resultSet.getDate("start_date").toLocalDate())
                        .finishDate(resultSet.getDate("finish_date").toLocalDate())
                        .build());
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        String query = """
                SELECT g.id   AS groupId,
                g.name        AS groupName,
                s.id          AS id,
                CONCAT(s.last_name,' ',s.first_name) AS fullName,
                g.name        AS name,
                 a.study_format   AS studyFormat,
                a.phone_number    AS phoneNumber,
                a.email       AS email
                FROM groups g
                LEFT JOIN accounts a ON g.id = a.student_group_id
                LEFT JOIN users s ON a.user_id = s.id
                WHERE g.id = ?
                """;

        List<GroupResponse> results = jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) -> {
            GroupResponse groupResponse = new GroupResponse();
            groupResponse.setId(rs.getLong("groupId"));
            groupResponse.setName(rs.getString("groupName"));

            if (rs.getObject("id") != null) {
                StudentsResponse studentsResponse = new StudentsResponse();
                studentsResponse.setId(rs.getLong("id"));
                studentsResponse.setFullName(rs.getString("fullName"));
                studentsResponse.setGroupName(rs.getString("name"));
                studentsResponse.setStudyFormat(StudyFormat.valueOf(rs.getString("studyFormat")));
                studentsResponse.setPhoneNumber(rs.getString("phoneNumber"));
                studentsResponse.setEmail(rs.getString("email"));

                groupResponse.setStudents(Collections.singletonList(studentsResponse));
            } else {
                groupResponse.setStudents(null);
            }
            return groupResponse;
        });

        if (results.isEmpty()) {
            throw new BadRequestException("Group with id %s not found.".formatted(id));
        }

        return results.get(0);
    }

}
