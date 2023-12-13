package com.zhurylo.testtaskkameleoon.entity;

import com.zhurylo.testtaskkameleoon.enums.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "votes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", initialValue = 508, sequenceName = "votes_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
   @Enumerated(EnumType.STRING)
    private VoteType type;

    @ManyToOne
    private Quote quote;
}
