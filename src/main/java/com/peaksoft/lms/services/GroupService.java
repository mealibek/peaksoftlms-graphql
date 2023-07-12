package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.group.GroupRequest;
import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;

import java.util.List;

public interface GroupService {
    GroupResponse save(GroupRequest request);

    List<GroupsResponse> getAll();

    GroupResponse getById(Long id);

    GroupResponse update(Long id, GroupRequest request);

    GroupResponse delete(Long id);
}
