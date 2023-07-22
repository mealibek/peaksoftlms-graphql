package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.lesson.LessonResponse;
import com.peaksoft.lms.dto.responses.lesson.LessonsResponse;
import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import com.peaksoft.lms.repositories.custom.CustomLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomLessonRepositoryImpl implements CustomLessonRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<LessonsResponse> getAll() {

        String sql = """
                SELECT
                s.id AS id,
                s.name AS name,
                vl.name AS video_lesson,
                p.name AS presentation,
                t.name AS task        
                FROM lessons s
                LEFT JOIN presentations p ON s.id = p.lesson_id
                LEFT JOIN video_lessons vl ON s.id = vl.lesson_id
                LEFT JOIN tasks t ON s.id = t.lesson_id                         
                """;
        return jdbcTemplate.query(sql, (resultSet, i) ->
                LessonsResponse.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .videoLesson(resultSet.getString("video_lesson"))
                        .presentation(resultSet.getString("presentation"))
                        .task(resultSet.getString("task"))
                        .build()
        );
    }

    @Override
    public Optional<LessonResponse> getById(Long id) {
        String sql = """
                SELECT
                l.id        AS  id,
                l.name      AS  name,
                vl.id       AS  video_id,
                vl.name     AS  video_name,
                vl.description    AS  video_description,
                p.id        AS  presentation_id,
                p.name      AS  presentation_name,
                p.description     AS  presentation_description,
                p.formatppt   AS  presentation_format
                FROM lessons l             
                LEFT JOIN video_lessons vl on l.id = vl.lesson_id
                LEFT JOIN presentations p on l.id = p.lesson_id
                WHERE l.id = ?    
                """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            LessonResponse lesson = new LessonResponse();
            lesson.setId(resultSet.getLong("id"));
            lesson.setName(resultSet.getString("name"));

            if (resultSet.getObject("video_id") != null){

                VideoLessonResponse videoLesson = new VideoLessonResponse();
                videoLesson.setId(resultSet.getLong("video_id"));
                videoLesson.setLessonId(resultSet.getLong("id"));
                videoLesson.setName(resultSet.getString("video_name"));
                videoLesson.setDescription(resultSet.getString("video_description"));

                lesson.setVideoLesson(videoLesson);
            }else {
                lesson.setVideoLesson(null);
            }
            if (resultSet.getObject("presentation_id") != null){

                PresentationResponse presentation = new PresentationResponse();
                presentation.setId(resultSet.getLong("presentation_id"));
                presentation.setLessonId(resultSet.getLong("id"));
                presentation.setName(resultSet.getString("presentation_name"));
                presentation.setDescription(resultSet.getString("presentation_description"));
                presentation.setFormatPPT(resultSet.getString("presentation_format"));

                lesson.setPresentations(Collections.singletonList(presentation));
            }else {
                lesson.setPresentations(new ArrayList<>());
            }

            return lesson;
        },id).stream().findAny();
    }
}
