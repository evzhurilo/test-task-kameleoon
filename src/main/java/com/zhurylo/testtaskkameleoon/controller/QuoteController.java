package com.zhurylo.testtaskkameleoon.controller;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.enums.VoteType;
import com.zhurylo.testtaskkameleoon.exception.QuoteNotFoundExcepiton;
import com.zhurylo.testtaskkameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class QuoteController {

    private final QuoteService service;

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuote(@PathVariable(name = "userId") Integer id, @RequestBody QuoteDto dto) {
        service.addQuote(dto, id);
    }

    //TODO implement method
    @PatchMapping("/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateQuote(@PathVariable(name = "id") Integer id, @RequestBody QuoteDto dto) {
        try {
            service.updateContent(id, dto.content());
        } catch (QuoteNotFoundExcepiton e) {
            e.getMessage();
        }
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable(name = "id") Integer id) {
        service.deleteQuote(id);
    }


    @PostMapping("/{id}/{vote-type}")
    @ResponseStatus(HttpStatus.OK)
    public void makeVote(@PathVariable(name = "id") Integer id, @PathVariable(name = "vote-type") String voteType) {
        service.makeVote(id, voteType);
    }

    @GetMapping("/worst")
    @ResponseStatus(HttpStatus.OK)
    public List<Quote> getTenWorstQuotes() {
        return service.getTenWorstQuotes();
    }

    @GetMapping("/best")
    @ResponseStatus(HttpStatus.OK)
    public List<Quote> getTenBestQuotes() {
        return service.getTenBestQuotes();
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRandomQuote());
    }
}