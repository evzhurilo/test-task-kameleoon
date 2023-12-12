package com.zhurylo.testtaskkameleoon.repository;

import com.zhurylo.testtaskkameleoon.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    Optional<Quote> findById(Integer id);

    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();

    @Query(value = "SELECT q.* " +
            "FROM quotes q " +
            "ORDER BY (SELECT COALESCE(SUM(CASE WHEN v.type = 'UPVOTE' THEN 1 ELSE 0 END), 0) - " +
            "COALESCE(SUM(CASE WHEN v.type = 'DOWNVOTE' THEN 1 ELSE 0 END), 0) " +
            "FROM votes v WHERE v.quote_id = q.id) DESC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Quote> findTop10QuotesByRating();

    @Query(value = "SELECT q.* " +
            "FROM quotes q " +
            "ORDER BY (SELECT COALESCE(SUM(CASE WHEN v.type = 'UPVOTE' THEN 1 ELSE 0 END), 0) - " +
            "COALESCE(SUM(CASE WHEN v.type = 'DOWNVOTE' THEN 1 ELSE 0 END), 0) " +
            "FROM votes v WHERE v.quote_id = q.id) ASC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Quote> findWorst10QuotesByRating();


}
