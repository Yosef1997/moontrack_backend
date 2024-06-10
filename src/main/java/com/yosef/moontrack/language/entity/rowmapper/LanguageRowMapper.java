package com.yosef.moontrack.language.entity.rowmapper;

import com.yosef.moontrack.language.entity.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LanguageRowMapper implements RowMapper<Language> {
  @Override
  public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
    Language language = new Language();
    language.setId(rs.getLong("id"));
    language.setLanguageName(rs.getString("language_name"));
    language.setCreatedAt(rs.getTimestamp("created_at").toInstant());
    language.setUpdatedAt(rs.getTimestamp("updated_at").toInstant());
    Timestamp deletedAtTimestamp = rs.getTimestamp("deleted_at");
    if (deletedAtTimestamp != null) {
      language.setDeletedAt(deletedAtTimestamp.toInstant());
    } else {
      language.setDeletedAt(null);
    }
    return language;
  }
}
