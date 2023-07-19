package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import com.peaksoft.lms.repositories.custom.CustomVideoLessonRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomVideoLessonRepositoryImpl implements CustomVideoLessonRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<VideoLessonResponse> getAll() {
    String sql = """
        SELECT
        vl.id            AS videoID,
        vl.name          AS videoName,
        vl.description   AS videoDescription,
        vl.lesson_id     AS lessonID,
        f.url            AS fileUrl
        FROM video_lessons vl JOIN files f on vl.file_id = f.id
        """;

    return jdbcTemplate.query(sql, (resultSet, i) -> VideoLessonResponse.builder()
        .id(resultSet.getLong("videoID"))
        .name(resultSet.getString("videoName"))
        .description(resultSet.getString("videoDescription"))
        .lessonId(resultSet.getLong("lessonID"))
        .url(resultSet.getString("fileUrl"))
        .build());
  }

  @Override
  public Optional<VideoLessonResponse> getById(Long id) {
    String sql = """
        SELECT
        vl.id            AS videoID,
        vl.name          AS videoName,
        vl.description   AS videoDescription,
        vl.lesson_id     AS lessonID,
        f.url            AS fileUrl
        FROM video_lessons vl JOIN files f on vl.file_id = f.id
        WHERE vl.id = ?
        """;

    return jdbcTemplate.query(sql, (resultSet, i) ->
        VideoLessonResponse.builder()
            .id(resultSet.getLong("videoID"))
            .name(resultSet.getString("videoName"))
            .description(resultSet.getString("videoDescription"))
            .lessonId(resultSet.getLong("lessonID"))
            .url(resultSet.getString("fileUrl"))
            .build(), id).stream().findAny();
  }
}
