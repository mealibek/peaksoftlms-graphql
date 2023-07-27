package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.lesson.LessonRequest;
import com.peaksoft.lms.dto.responses.lesson.LessonResponse;
import com.peaksoft.lms.dto.responses.lesson.LessonsResponse;
import com.peaksoft.lms.services.LessonService;
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
public class LessonController {
    private final LessonService lessonService;

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @MutationMapping(name = "saveLesson")
    public LessonResponse saveLesson(@Argument @Valid LessonRequest request) {
        return lessonService.save(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @QueryMapping(name = "getLessons")
    public List<LessonsResponse> getLessons() {
        return lessonService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @QueryMapping(name = "getLesson")
    public LessonResponse getLessonById(@Argument Long id) {
        return lessonService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @MutationMapping(name = "updateLesson")
    public LessonResponse updateLesson(@Argument @Valid LessonRequest request, @Argument Long id) {
        return lessonService.update(request, id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @MutationMapping(name = "deleteLesson")
    public String deleteLesson(@Argument Long id) {
        return lessonService.delete(id);
    }
}

