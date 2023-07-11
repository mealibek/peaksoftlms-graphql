package com.peaksoft.lms.repositories;

import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("""
            select new com.peaksoft.lms.dto.responses.group.GroupsResponse
            (g.id,g.name,g.description,f.url,g.startDate,g.finishDate)
            FROM Group g
            LEFT JOIN g.file f
            """)
    List<GroupsResponse> getAll();
}