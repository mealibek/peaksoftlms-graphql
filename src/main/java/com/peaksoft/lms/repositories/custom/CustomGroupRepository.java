package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;

import java.util.List;
import java.util.Optional;

public interface CustomGroupRepository {

  List<GroupsResponse> getAll();

  Optional<GroupResponse> getGroupById(Long id);
}

