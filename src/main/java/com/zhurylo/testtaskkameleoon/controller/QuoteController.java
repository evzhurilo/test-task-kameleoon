package com.zhurylo.testtaskkameleoon.controller;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.exception.QuoteNotFoundExcepiton;
import com.zhurylo.testtaskkameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QuoteController {

    private final QuoteService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuote(@RequestBody QuoteDto dto) {
        service.addQuote(dto);
    }

    //TODO implement method
    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateQuote(@PathVariable(name = "id") Integer id, @RequestBody QuoteDto dto) {
        try {
            service.updateContent(id, dto.content());
        } catch (QuoteNotFoundExcepiton e) {
            e.getMessage(String.format("Quote with id: %s not found", id));
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable(name = "id") Integer id) {
        service.deleteQuote(id);
    }

    @GetMapping()
    public ResponseEntity<?> getTenWorstQuotes() {
        return service.showWorstQuotes();
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRandomQuote());
    }
}