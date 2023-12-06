package com.zhurylo.testtaskkameleoon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "quote")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false, unique = true)
    private String id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "date_of_creation", nullable = false, updatable = true)
    private LocalDateTime creationDate;
    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
