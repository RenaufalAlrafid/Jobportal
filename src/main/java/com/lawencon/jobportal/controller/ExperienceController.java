package com.lawencon.jobportal.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.helper.ResponseHelper;
import com.lawencon.jobportal.model.response.ConstantResponse;
import com.lawencon.jobportal.model.response.WebResponse;
import com.lawencon.jobportal.service.ExperienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Experience", description = "Experience API endpoint")
@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
public class ExperienceController {
    private final ExperienceService service;

    @GetMapping(value = "/experience", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<List<ConstantResponse>>> findAll() {
        SessionHelper.validateRole("SA");
        return ResponseEntity.ok(ResponseHelper.ok(service.getAll()));
    }
}
