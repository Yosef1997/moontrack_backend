package com.yosef.moontrack.language;

import com.yosef.moontrack.language.entity.Language;
import com.yosef.moontrack.language.service.LanguageService;
import com.yosef.moontrack.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/language")
@Log
public class LanguageController {

  private final LanguageService languageService;

  public LanguageController(LanguageService languageService) {
    this.languageService = languageService;
  }

  @GetMapping("/")
  public ResponseEntity<Response<List<Language>>> getLanguages() {
    return Response.successResponse("All languages fetched", languageService.getLanguages());
  }

  @PostMapping("/")
  public ResponseEntity<Response<Language>> createLanguage(@RequestBody Language language) {
    return Response.successResponse("Create language success", languageService.createLanguage(language));
  }
}
