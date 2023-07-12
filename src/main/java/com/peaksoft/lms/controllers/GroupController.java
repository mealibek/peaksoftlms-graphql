package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.group.GroupRequest;
import com.peaksoft.lms.dto.responses.group.GroupResponse;
import com.peaksoft.lms.dto.responses.group.GroupsResponse;
import com.peaksoft.lms.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class GroupController {

    private final GroupService groupService;

    @MutationMapping(name = "save")
    public GroupResponse save(@Argument GroupRequest request){
        return groupService.save(request);
    }

    @QueryMapping(name = "getAll")
    public List<GroupsResponse> getAll(){
        return groupService.getAll();
    }

    @QueryMapping(name = "getById")
    public GroupResponse getById(@Argument Long id){
        return groupService.getById(id);
    }

    @MutationMapping(name = "update")
    public GroupResponse update(@Argument Long id,@Argument GroupRequest request){
        return groupService.update(id,request);
    }

    @QueryMapping(name = "delete")
    public GroupResponse delete(@Argument Long id){
        return groupService.delete(id);
    }
}
