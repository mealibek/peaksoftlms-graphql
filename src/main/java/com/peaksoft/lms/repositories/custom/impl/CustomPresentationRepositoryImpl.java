package com.peaksoft.lms.repositories.custom.impl;


import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import com.peaksoft.lms.repositories.custom.CustomPresentationRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class CustomPresentationRepositoryImpl implements CustomPresentationRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Optional<PresentationResponse> getById(Long id) {
    String sql = """
        SELECT
        p.id      AS pID,
        p.name    AS pName,
        p.description AS pDescription,
        p.formatppt   AS pFormatPPT,
        p.lesson_id   AS pLessonID
        FROM presentations p
        WHERE p.id = ?
        """;

    return jdbcTemplate.query(sql, (resultSet, i) -> PresentationResponse.builder()
        .id(resultSet.getLong("pID"))
        .name(resultSet.getString("pName"))
        .description(resultSet.getString("pDescription"))
        .lessonId(resultSet.getLong("pLessonID"))
        .formatPPT(resultSet.getString("pFormatPPT"))
        .build(), id).stream().findAny();
  }

  @Override
  public List<PresentationResponse> getAll() {
    String sql = """
        SELECT
        p.id      AS pID,
        p.name    AS pName,
        p.description AS pDescription,
        p.formatppt   AS pFormatPPT,
        p.lesson_id   AS pLessonID
        FROM presentations p
        """;

    return jdbcTemplate.query(sql, (resultSet, i) -> PresentationResponse.builder()
        .id(resultSet.getLong("pID"))
        .name(resultSet.getString("pName"))
        .description(resultSet.getString("pDescription"))
        .lessonId(resultSet.getLong("pLessonID"))
        .formatPPT(resultSet.getString("pFormatPPT"))
        .build());
  }
}
