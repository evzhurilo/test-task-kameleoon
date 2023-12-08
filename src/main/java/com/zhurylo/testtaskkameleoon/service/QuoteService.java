package com.zhurylo.testtaskkameleoon.service;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.exception.QuoteNotFoundExcepiton;
import com.zhurylo.testtaskkameleoon.repository.QuoteRepository;
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
    private final QuoteRepository quoteRepository;

    @Transactional
    public void addQuote(QuoteDto dto) {
        Quote quote = Quote.builder()
                .content(dto.content())
                .creationDate(LocalDateTime.now())
                .build();
        quoteRepository.save(quote);
    }

    public Optional<Quote> findQuote(Integer id) throws QuoteNotFoundExcepiton {
        if (quoteRepository.findById(id).isEmpty()) {
            throw new QuoteNotFoundExcepiton("Quote not found");
        }
        return quoteRepository.findById(id);
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

}