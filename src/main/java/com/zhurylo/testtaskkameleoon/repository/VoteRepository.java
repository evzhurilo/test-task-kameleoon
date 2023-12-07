package com.zhurylo.testtaskkameleoon.repository;

import com.zhurylo.testtaskkameleoon.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findByType(String type);
}
