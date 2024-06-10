package com.yosef.moontrack.language.service;

import com.yosef.moontrack.language.entity.Language;

import java.util.List;

public interface LanguageService {
  List<Language> getLanguages();
  Language createLanguage(Language language);
}
