package com.training.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "GOOD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private double price;
}
