package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.group.GroupResponse;

public interface GroupCustomRepository {
    GroupResponse getGroupById(Long id);
}

