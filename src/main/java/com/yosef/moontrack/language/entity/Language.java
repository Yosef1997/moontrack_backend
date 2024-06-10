package com.yosef.moontrack.language.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Getter
@Setter
@Data
@Table(name = "language_list", schema = "moontrack")
public class Language {
  @Id
  private Long id;

  @NotBlank(message = "Language name is required")
  @Column(value = "language_name")
  private String languageName;

  @Column(value = "created_at")
  private Instant createdAt;

  @Column(value = "updated_at")
  private Instant updatedAt;

  @Column(value = "deleted_at")
  private Instant deletedAt;
}
