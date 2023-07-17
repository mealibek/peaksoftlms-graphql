package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;

import java.util.List;

public interface CustomGroupRepository {
    List<GroupsResponse> getAll();
    GroupResponse getGroupById(Long id);
}

