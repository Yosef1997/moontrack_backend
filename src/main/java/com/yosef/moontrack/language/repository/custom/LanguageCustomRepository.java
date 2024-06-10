package com.yosef.moontrack.language.repository.custom;

import com.yosef.moontrack.language.entity.Language;
import com.yosef.moontrack.language.entity.rowmapper.LanguageRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageCustomRepository {
  private final JdbcTemplate jdbcTemplate;

  public LanguageCustomRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Language> findAllLanguages() {
    String sql = "SELECT * FROM moontrack.language_list";
    RowMapper<Language> rowMapper = new LanguageRowMapper();
    return jdbcTemplate.query(sql, rowMapper).stream().toList();
  }
}
