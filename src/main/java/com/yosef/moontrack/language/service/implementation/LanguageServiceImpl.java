package com.yosef.moontrack.language.service.implementation;

import com.yosef.moontrack.language.entity.Language;
import com.yosef.moontrack.language.repository.LanguageRepository;
import com.yosef.moontrack.language.repository.custom.LanguageCustomRepository;
import com.yosef.moontrack.language.service.LanguageService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Log
public class LanguageServiceImpl implements LanguageService {
  private final LanguageRepository languageRepository;
  private final LanguageCustomRepository languageCustomRepository;

  public LanguageServiceImpl (LanguageRepository languageRepository, LanguageCustomRepository languageCustomRepository) {
    this.languageRepository = languageRepository;
    this.languageCustomRepository = languageCustomRepository;
  }

  @Override
  public List<Language> getLanguages() {
    return languageCustomRepository.findAllLanguages();
  }

  @Override
  public Language createLanguage(Language language) {
    language.setCreatedAt(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0)));
    language.setUpdatedAt(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0)));
    return languageRepository.save(language);
  }

}
