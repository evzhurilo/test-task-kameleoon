package com.zhurylo.testtaskkameleoon.entity;

import com.zhurylo.testtaskkameleoon.enums.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
   @Enumerated(EnumType.STRING)
    private VoteType type;

    @ManyToOne
    private Quote quote;
}
