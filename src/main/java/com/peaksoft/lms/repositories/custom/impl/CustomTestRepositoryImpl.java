package com.peaksoft.lms.repositories.custom.impl;


import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.dto.responses.test.OptionResponse;
import com.peaksoft.lms.dto.responses.test.QuestionResponse;
import com.peaksoft.lms.dto.responses.test.ResultResponse;
import com.peaksoft.lms.dto.responses.test.TestResponse;
import com.peaksoft.lms.enums.OptionType;
import com.peaksoft.lms.enums.Role;
import com.peaksoft.lms.repositories.custom.CustomTestRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomTestRepositoryImpl implements CustomTestRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<TestResponse> getTestsByLesson(Long lessonId, Long userId) {
    String sql = """
        SELECT
        t.id      AS testID,
        l.id      AS lessonID,
        t.name    AS testName,
        q.id      AS questionID,
        q.name    AS questionName,
        q.option_type AS questionOptionType,
        q.question_order  AS questionOrder,
        o.id      AS optionID,
        o.name    AS optionName,
        o.is_correct  AS optionIsCorrect,
        o.option_order  AS optionOrder,
        r.id      AS resultID,
        r.count_correct AS correctAnswers,
        r.count_in_correct  AS inCorrectAnswers,
        a.id      AS accountID,
        a.email   AS accountEmail,
        a.role    AS accountRole
        FROM tests t
        JOIN questions q on t.id = q.test_id
        JOIN options o on q.id = o.question_id
        LEFT JOIN results r on t.id = r.test_id
        JOIN accounts a on a.id = r.account_id
        JOIN lessons l on l.id = t.lesson_id
        WHERE l.id = ? AND a.id = ?
        """;

    return jdbcTemplate.query(sql, (rs, i) -> {
      TestResponse testResponse = new TestResponse();
      testResponse.setId(rs.getLong("testID"));
      testResponse.setLessonId(rs.getLong("lessonID"));
      testResponse.setName(rs.getString("testName"));

      List<QuestionResponse> questionResponses = new ArrayList<>();
      do {
        // todo: question
        if (rs.getObject("questionID") != null) {
          Long questionID = rs.getLong("questionID");
          String questionName = rs.getString("questionName");
          OptionType questionOptionType = OptionType.valueOf(rs.getString("questionOptionType"));
          Integer questionOrder = rs.getInt("questionOrder");

          QuestionResponse questionResponse = QuestionResponse.builder()
              .id(questionID)
              .name(questionName)
              .optionType(questionOptionType)
              .order(questionOrder)
              .build();

          List<OptionResponse> optionResponses = new ArrayList<>();

          // todo: option
          if (rs.getObject("optionID") != null) {
            OptionResponse optionResponse = OptionResponse.builder()
                .id(rs.getLong("optionID"))
                .isCorrect(rs.getBoolean("optionIsCorrect"))
                .name(rs.getString("optionName"))
                .order(rs.getInt("optionOrder"))
                .build();
            optionResponses.add(optionResponse);
          }
          List<OptionResponse> filteredOptions = optionResponses.stream().sorted(
              Comparator.comparing(OptionResponse::getOrder)).toList();
          questionResponse.setOptions(filteredOptions);

          questionResponses.add(questionResponse);
        }

        // todo: result
        if (rs.getObject("resultID") != null) {
          ResultResponse resultResponse = ResultResponse.builder()
              .id(rs.getLong("resultID"))
              .testId(rs.getLong("testID"))
              .correctAnswers(rs.getInt("correctAnswers"))
              .inCorrectAnswers(rs.getInt("inCorrectAnswers"))
              .build();

          // todo: account
          if (rs.getObject("accountEmail") != null) {
            AuthResponse authResponse = AuthResponse.builder()
                .email(rs.getString("accountEmail"))
                .role(Role.valueOf(rs.getString("accountRole")))
                .build();
            resultResponse.setAccount(authResponse);
          }

          resultResponse.setScore(0.0F);
          testResponse.setResult(resultResponse);
        }

        testResponse.setQuestions(questionResponses);
      } while (rs.next());
      return testResponse;
    }, lessonId, userId);
  }

  @Override
  public Optional<TestResponse> getTestById(Long id) {
    String sql = """
        SELECT
        t.id      AS testID,
        l.id      AS lessonID,
        t.name    AS testName,
        q.id      AS questionID,
        q.name    AS questionName,
        q.option_type AS questionOptionType,
        q.question_order  AS questionOrder,
        o.id      AS optionID,
        o.name    AS optionName,
        o.is_correct  AS optionIsCorrect,
        o.option_order  AS optionOrder
        FROM tests t
        JOIN questions q on t.id = q.test_id
        JOIN options o on q.id = o.question_id
        JOIN lessons l on l.id = t.lesson_id
        WHERE t.id  = ?
        """;

    return jdbcTemplate.query(sql, (rs, i) -> {
      TestResponse testResponse = new TestResponse();
      testResponse.setId(rs.getLong("testID"));
      testResponse.setLessonId(rs.getLong("lessonID"));
      testResponse.setName(rs.getString("testName"));

      List<QuestionResponse> questionResponses = new ArrayList<>();
      do {
        // todo: question
        if (rs.getObject("questionID") != null) {
          Long questionID = rs.getLong("questionID");
          String questionName = rs.getString("questionName");
          OptionType questionOptionType = OptionType.valueOf(rs.getString("questionOptionType"));
          Integer questionOrder = rs.getInt("questionOrder");

          QuestionResponse questionResponse = QuestionResponse.builder()
              .id(questionID)
              .name(questionName)
              .optionType(questionOptionType)
              .order(questionOrder)
              .build();

          List<OptionResponse> optionResponses = new ArrayList<>();

          // todo: option
          if (rs.getObject("optionID") != null) {
            OptionResponse optionResponse = OptionResponse.builder()
                .id(rs.getLong("optionID"))
                .isCorrect(rs.getBoolean("optionIsCorrect"))
                .name(rs.getString("optionName"))
                .order(rs.getInt("optionOrder"))
                .build();
            optionResponses.add(optionResponse);
          }
          List<OptionResponse> filteredOptions = optionResponses.stream().sorted(
              Comparator.comparing(OptionResponse::getOrder)).toList();
          questionResponse.setOptions(filteredOptions);

          questionResponses.add(questionResponse);
        }
        testResponse.setQuestions(questionResponses);
      } while (rs.next());
      return testResponse;
    }, id).stream().findAny();
  }
}



