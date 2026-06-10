package me.vmachohan.data_api;

import me.vmachohan.data_api.dto.TransformRequest;
import me.vmachohan.data_api.dto.TransformResponse;
import me.vmachohan.data_api.transformer.TextTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transform")
public class TransformController {

    @Value("${internal.secret}")
    private String internalToken;

    @PostMapping
    public ResponseEntity<TransformResponse> transform(
            @RequestHeader("X-Internal-Token") String token,
            @RequestBody TransformRequest request) {

        if (!internalToken.equals(token)) {
            return ResponseEntity.status(403).build();
        }

        String result = TextTransformer.reverseAndUpper(request.text());

        return ResponseEntity.ok(new TransformResponse(result));
    }
}
