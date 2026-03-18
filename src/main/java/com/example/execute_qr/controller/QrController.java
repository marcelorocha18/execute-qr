package com.example.execute_qr.controller;

import com.example.execute_qr.model.QrRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/execute-qr")
public class QrController {

    private static final Logger log = LoggerFactory.getLogger(QrController.class);

    @PostMapping
    public ResponseEntity<?> executeQR(
            @RequestHeader("traceId") String traceId,
            @RequestHeader("x-http-answer") Integer httpAnswer,
            @RequestBody QrRequest request
    ) {

        log.info("REQUEST traceId={} body={}", traceId, request);

        if (httpAnswer == 200) {

            Map<String,Object> response = new HashMap<>();

            response.put("level","INFO");
            response.put("time", Instant.now().toString());
            response.put("pid",1);
            response.put("hostname","execute-qr");
            response.put("traceId",traceId);
            response.put("[RESPONSE IMAGE]","Sending image, size: 83274 bytes");

            log.info("RESPONSE traceId={} body={}", traceId, response);

            return ResponseEntity.ok(response);
        }

        if (httpAnswer == 400) {

            Map<String,Object> response = new HashMap<>();
            response.put("status",400);
            response.put("message","bad request");

            log.error("RESPONSE traceId={} body={}", traceId, response);

            return ResponseEntity.status(400).body(response);
        }

        if (httpAnswer == 500) {

            Map<String,Object> response = new HashMap<>();
            response.put("status",500);
            response.put("contentType","image/jpeg");
            response.put("isBinary",true);

            log.error("RESPONSE traceId={} body={}", traceId, response);

            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.badRequest().build();
    }
}