package com.zhurylo.testtaskkameleoon.service;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.entity.Vote;
import com.zhurylo.testtaskkameleoon.enums.VoteType;
import com.zhurylo.testtaskkameleoon.exception.QuoteNotFoundExcepiton;
import com.zhurylo.testtaskkameleoon.repository.QuoteRepository;
import com.zhurylo.testtaskkameleoon.repository.UserRepository;
import com.zhurylo.testtaskkameleoon.repository.VoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QuoteService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final QuoteRepository quoteRepository;

    @NonNull
    private final VoteRepository voteRepository;

    //TODO solve problem with optional type of user in Builder of Quote
    @Transactional
    public void addQuote(QuoteDto dto, Integer id) {
        if (userRepository.findById(id).isPresent()) {
            Quote quote = Quote.builder()
                    .content(dto.content())
                    .creationDate(LocalDateTime.now())
                    .user(userRepository.findById(id).get())
                    .build();
            quoteRepository.save(quote);
        }
    }

    public void updateContent(Integer id, String content) throws QuoteNotFoundExcepiton {
        if (quoteRepository.findById(id).isEmpty()) {
            throw new QuoteNotFoundExcepiton("Quote not found");
        }
        quoteRepository.findById(id).get().setContent(content);
        quoteRepository.findById(id).get().setUpdateDate(LocalDateTime.now());
    }

    public void deleteQuote(Integer id) {
        quoteRepository.deleteById(id);
    }

    public Optional<Quote> getRandomQuote() {
        return quoteRepository.findRandomQuote();
    }

    //TODO implement method
    public List<Quote> getTenBestQuotes() {
        return quoteRepository.findTop10QuotesByRating();
    }

    public List<Quote> getTenWorstQuotes() {
        return quoteRepository.findWorst10QuotesByRating();
    }

    public void makeVote(Integer id, String voteType) {
        if (quoteRepository.findById(id).isPresent())
            switch (voteType) {
                case "like" -> voteRepository.save(Vote.builder()
                        .quote(quoteRepository.findById(id).get())
                        .type(VoteType.UPVOTE)
                        .build());
                case "dislike" -> voteRepository.save(Vote.builder()
                        .quote(quoteRepository.findById(id).get())
                        .type(VoteType.DOWNVOTE)
                        .build());
            }
    }
}