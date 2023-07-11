package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.group.GroupRequest;
import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.enums.FileType;
import com.peaksoft.lms.models.File;
import com.peaksoft.lms.models.Group;
import com.peaksoft.lms.repositories.GroupRepository;
import com.peaksoft.lms.repositories.custom.GroupCustomRepository;
import com.peaksoft.lms.services.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final GroupCustomRepository groupCustomRepository;

    @Override
    public GroupResponse save(GroupRequest request) {

        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .file(File.builder()
                        .fileType(FileType.IMAGE)
                        .url(request.getImage())
                        .build())
//                .startDate(request.getDate())
                .build();
        repository.save(group);
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }

    @Override
    public List<GroupsResponse> getAll() {
        return repository.getAll();
    }
}
