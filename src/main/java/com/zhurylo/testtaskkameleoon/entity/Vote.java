package com.zhurylo.testtaskkameleoon.entity;

import com.zhurylo.testtaskkameleoon.enums.VoteType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private VoteType type;

    @Column(name = "counter")
    private Integer counter;

    @ManyToOne
    private Quote quote;
}