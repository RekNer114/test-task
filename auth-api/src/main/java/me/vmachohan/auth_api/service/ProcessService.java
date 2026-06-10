package me.vmachohan.auth_api.service;

import lombok.RequiredArgsConstructor;
import me.vmachohan.auth_api.dto.process.ProcessRequest;
import me.vmachohan.auth_api.dto.process.ProcessResponse;
import me.vmachohan.auth_api.entity.ProcessingLog;
import me.vmachohan.auth_api.entity.User;
import me.vmachohan.auth_api.repository.ProcessingLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessingLogRepository logRepository;
    private final WebClient client;

    @Value("${internal.secret}")
    private String internalToken;


    public String process(String text, User current){
        ProcessResponse response = client.post()
                .uri("/api/transform")
                .header("X-Internal-Token", internalToken)
                .bodyValue(new ProcessRequest(text))
                .retrieve()
                .bodyToMono(ProcessResponse.class)
                .block();

        If 

        String result = response.result();


        ProcessingLog log = ProcessingLog.builder()
                .user(current)
                .inputText(text)
                .outputText(result)
                .build();

        logRepository.save(log);

        return result;
    }
}
