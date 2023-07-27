package com.peaksoft.lms.repositories.custom.impl;

import com.peaksoft.lms.dto.responses.task.TaskResponse;
import com.peaksoft.lms.repositories.custom.CustomTaskRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<TaskResponse> getAll() {

    String sql = """
        SELECT
        t.id        AS taskID,
        t.name      AS taskName,
        t.description AS taskDescription,
        t.dead_line   AS taskDeadLine,
        l.id          AS lessonID
        FROM tasks t
        JOIN lessons l on t.lesson_id = l.id
        """;
    return jdbcTemplate.query(sql, (rs, i) -> TaskResponse.builder()
        .id(rs.getLong("taskID"))
        .lessonId(rs.getLong("lessonID"))
        .htmlContent(rs.getString("taskDescription"))
        .name(rs.getString("taskName"))
        .deadLine(rs.getDate("taskDeadLine").toLocalDate())
        .build());
  }

  @Override
  public Optional<TaskResponse> getById(Long id) {

    String sql = """
        SELECT
        t.id        AS taskID,
        t.name      AS taskName,
        t.description AS taskDescription,
        t.dead_line   AS taskDeadLine,
        l.id          AS lessonID
        FROM tasks t
        JOIN lessons l on t.lesson_id = l.id
        WHERE t.id  = ?
        """;

    return jdbcTemplate.query(sql, (rs, i) -> TaskResponse.builder()
        .id(rs.getLong("taskID"))
        .lessonId(rs.getLong("lessonID"))
        .htmlContent(rs.getString("taskDescription"))
        .name(rs.getString("taskName"))
        .deadLine(rs.getDate("taskDeadLine").toLocalDate())
        .build(), id).stream().findAny();
  }
}
