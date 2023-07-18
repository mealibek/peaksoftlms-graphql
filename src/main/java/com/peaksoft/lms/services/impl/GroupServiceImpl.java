package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.group.GroupRequest;
import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.enums.FileType;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.File;
import com.peaksoft.lms.models.Group;
import com.peaksoft.lms.repositories.GroupRepository;
import com.peaksoft.lms.repositories.custom.CustomGroupRepository;
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
    private final CustomGroupRepository groupCustomRepository;

    @Override
    public GroupResponse save(GroupRequest request) {

        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .file(File.builder()
                        .fileType(FileType.IMAGE)
                        .url(request.getImage())
                        .build())
                .startDate(request.getStartDate())
                .finishDate(request.getStartDate().plusMonths(9))
                .build();
        repository.save(group);
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }

    @Override
    public List<GroupsResponse> getAll() {
        return groupCustomRepository.getAll();
    }

    @Override
    public GroupResponse getById(Long id) {
        return groupCustomRepository.getGroupById(id);
    }

    @Override
    public GroupResponse update(Long id, GroupRequest request) {

        Group group = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Group with id %s not found.".formatted(id)));

        group.setName(request.getName() != null && !request.getName().equals(group.getName()) ? request.getName() : group.getName());
        group.setDescription(request.getDescription() != null && !request.getDescription().equals(group.getDescription()) ? request.getDescription() : group.getDescription());
        group.getFile().setUrl(request.getImage() != null && !request.getImage().equals(group.getFile().getUrl()) ? request.getImage() : group.getFile().getUrl());
        group.setStartDate(request.getStartDate() != null && !request.getStartDate().equals(group.getStartDate()) ? request.getStartDate() : group.getStartDate());
        repository.save(group);

        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }

    @Override
    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Group with id %s not found.".formatted(id));
        }
        repository.deleteById(id);
        return "Group with id %s successfully deleted.".formatted(id);
    }
}
