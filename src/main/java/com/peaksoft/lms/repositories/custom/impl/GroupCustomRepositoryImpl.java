package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.student.GroupStudentsResponse;
import com.peaksoft.lms.enums.StudyFormat;
import com.peaksoft.lms.exceptions.BadRequestException;
import com.peaksoft.lms.repositories.custom.GroupCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupCustomRepositoryImpl implements GroupCustomRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public GroupResponse getGroupById(Long id) {
        String query = "SELECT g.id as groupId, g.name as groupName, s.id as id, concat(s.last_name,' ',s.first_name) as fullName, g.name as name, a.study_format as studyFormat, a.phone_number as phoneNumber, a.email as email " +
                "FROM groups g " +
                "LEFT JOIN accounts a ON g.id = a.student_group_id " +
                "LEFT JOIN users s on a.user_id = s.id " +
                "WHERE g.id = ?";

        List<GroupResponse> results = jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) -> {
            GroupResponse groupResponse = new GroupResponse();
            groupResponse.setId(rs.getLong("groupId"));
            groupResponse.setName(rs.getString("groupName"));

            if (rs.getObject("id") != null) {
                GroupStudentsResponse studentsResponse = new GroupStudentsResponse();
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
