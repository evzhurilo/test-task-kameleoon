package com.zhurylo.testtaskkameleoon.controller;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.service.QuoteService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QuoteController {

    private final QuoteService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuote(@RequestBody QuoteDto dto) {
        return ResponseEntity.ok(dto);
    }

    //TODO implement method
    public ResponseEntity<?> updateQuote(@RequestBody QuoteDto dto) {
        return ResponseEntity.ok(dto);
    }

    //TODO implement method
    public ResponseEntity<?> getQuotes(@RequestBody QuoteDto dto) {
        return ResponseEntity.ok(dto);
    }

    //TODO implement method
    public ResponseEntity<?> getRandomQuote(@RequestBody QuoteDto dto) {
        return ResponseEntity.ok(dto);
    }
}