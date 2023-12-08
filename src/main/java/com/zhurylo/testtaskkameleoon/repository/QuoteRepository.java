package com.zhurylo.testtaskkameleoon.repository;

import com.zhurylo.testtaskkameleoon.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    Optional<Quote> findById(Integer id);

    @Query(value = "SELECT * FROM quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();
//
//    @Query("SELECT q FROM Quote q JOIN FETCH q.votes v GROUP BY q.id ORDER BY (SUM(CASE WHEN v.type = 'UPVOTE' THEN v.counter ELSE 0 END) - SUM(CASE WHEN v.type = 'DOWNVOTE' THEN v.counter ELSE 0 END)) DESC")
//    List<Quote> findTop10QuotesByRating();
//
//    @Query("SELECT q FROM Quote q JOIN FETCH q.votes v GROUP BY q.id ORDER BY (SUM(CASE WHEN v.type = 'UPVOTE' THEN v.counter ELSE 0 END) - SUM(CASE WHEN v.type = 'DOWNVOTE' THEN v.counter ELSE 0 END)) ASC")
//    List<Quote> findWorst10QuotesByRating();

    @Query(value = "SELECT * FROM quote ORDER BY (SELECT COALESCE(SUM(CASE WHEN v.type = 'UPVOTE' THEN v.counter ELSE 0 END), 0) - COALESCE(SUM(CASE WHEN v.type = 'DOWNVOTE' THEN v.counter ELSE 0 END), 0) FROM vote v WHERE v.quote_id = q.id) DESC LIMIT 10", nativeQuery = true)
    List<Quote> findTop10QuotesByRating();

    @Query(value = "SELECT * FROM quote ORDER BY (SELECT COALESCE(SUM(CASE WHEN v.type = 'UPVOTE' THEN v.counter ELSE 0 END), 0) - COALESCE(SUM(CASE WHEN v.type = 'DOWNVOTE' THEN v.counter ELSE 0 END), 0) FROM vote v WHERE v.quote_id = q.id) ASC LIMIT 10", nativeQuery = true)
    List<Quote> findWorst10QuotesByRating();


}
