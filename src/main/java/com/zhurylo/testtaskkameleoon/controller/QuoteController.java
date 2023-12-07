package com.zhurylo.testtaskkameleoon.controller;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.enums.VoteType;
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
    @PatchMapping("/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateQuote(@PathVariable(name = "id") Integer id, @RequestBody QuoteDto dto) {
        try {
            service.updateContent(id, dto.content());
        } catch (QuoteNotFoundExcepiton e) {
            e.getMessage(String.format("Quote with id: %s not found", id));
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
        switch (voteType) {
            case ("like"):
                try {
                    if (service.findQuote(id).isPresent())
                        service.findQuote(id).get().getVotes().stream()
                                .filter(v -> v.getType() == VoteType.UPVOTE).toList().get(0).setCounter(+1);
                } catch (QuoteNotFoundExcepiton e) {
                    e.getMessage("Quote not found");
                }
            case ("dislike"):
                try {
                    if (service.findQuote(id).isPresent())
                        service.findQuote(id).get().getVotes().stream()
                                .filter(v -> v.getType() == VoteType.DOWNVOTE).toList().get(0).setCounter(+1);
                } catch (QuoteNotFoundExcepiton e) {
                    e.getMessage("Quote not found");
                }
        }
    }
//    @GetMapping()
//    public ResponseEntity<?> getTenWorstQuotes() {
//        return service.showTenWorstQuotes();
//    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRandomQuote());
    }
}