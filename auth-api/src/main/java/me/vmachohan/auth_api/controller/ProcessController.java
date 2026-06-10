package me.vmachohan.auth_api.controller;

import lombok.RequiredArgsConstructor;
import me.vmachohan.auth_api.dto.process.ProcessRequest;
import me.vmachohan.auth_api.dto.process.ProcessResponse;
import me.vmachohan.auth_api.entity.User;
import me.vmachohan.auth_api.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/process")
@RequiredArgsConstructor
@RestController
public class ProcessController {

    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<ProcessResponse> process(
            @RequestBody ProcessRequest request,
            @AuthenticationPrincipal User current) {

        String result = processService.process(request.text(), current);
        return ResponseEntity.ok(new ProcessResponse(result));
    }
}


