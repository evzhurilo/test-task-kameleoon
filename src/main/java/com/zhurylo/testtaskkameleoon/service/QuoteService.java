package com.zhurylo.testtaskkameleoon.service;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.exception.QuoteNotFoundExcepiton;
import com.zhurylo.testtaskkameleoon.repository.QuoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QuoteService {

    @NonNull
    private final QuoteRepository repository;

    @Transactional
    public void addQuote(QuoteDto dto) {
        Quote quote = Quote.builder()
                .content(dto.content())
                .creationDate(LocalDateTime.now())
                .build();
        repository.save(quote);
    }

//    public Optional<Quote> findQuote(Integer id) throws QuoteNotFoundExcepiton {
//        if (repository.findById(id).isEmpty()) {
//            throw new QuoteNotFoundExcepiton();
//        }
//        return repository.findById(id);
//    }

    public void updateContent(Integer id, String content) throws QuoteNotFoundExcepiton {
        if (repository.findById(id).isEmpty()) {
            throw new QuoteNotFoundExcepiton();
        }
        repository.findById(id).get().setContent(content);
        repository.findById(id).get().setUpdateDate(LocalDateTime.now());
    }

    public void deleteQuote(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Quote> getRandomQuote() {
        return repository.findById(new Random().nextInt((int) repository.count() + 1));
    }
    
}