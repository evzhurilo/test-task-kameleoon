package com.zhurylo.testtaskkameleoon.service;

import com.zhurylo.testtaskkameleoon.dto.QuoteDto;
import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.repository.QuoteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class QuoteService {

    @NonNull
    private final QuoteRepository repository;

    public void addQuote(QuoteDto dto) {
        Quote quote = Quote.builder()
                .content(dto.content())
                .creationDate(LocalDateTime.now())
                .build();
    }
}