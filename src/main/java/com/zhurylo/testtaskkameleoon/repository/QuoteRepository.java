package com.zhurylo.testtaskkameleoon.repository;

import com.zhurylo.testtaskkameleoon.entity.Quote;
import com.zhurylo.testtaskkameleoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, String> {


}
