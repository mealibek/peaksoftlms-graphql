package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.group.GroupRequest;
import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.services.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAuthority('ADMIN')")
public class GroupController {

    private final GroupService groupService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "saveGroup")
    public GroupResponse save(@Argument @Valid GroupRequest request) {
        return groupService.save(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','INSTRUCTOR')")
    @QueryMapping(name = "getGroups")
    public List<GroupsResponse> getAll() {
        return groupService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','INSTRUCTOR')")
    @QueryMapping(name = "getGroupById")
    public GroupResponse getById(@Argument Long id) {
        return groupService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "updateGroup")
    public GroupResponse update(@Argument Long id, @Argument @Valid GroupRequest request) {
        return groupService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "deleteGroup")
    public String delete(@Argument Long id) {
        return groupService.delete(id);
    }
}
