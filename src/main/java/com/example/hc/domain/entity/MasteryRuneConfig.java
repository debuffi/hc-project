package com.example.hc.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@Data
@Entity
@Table(name = "mastery_rune_config")
public class MasteryRuneConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mastery_rune_id")
    private MasteryRune masteryRune;
    @Column(name = "mastery_rune_id", insertable = false, updatable = false)
    private Long masteryRuneId;
    private Integer lvl;
    private Integer value;
    @Column(name = "next_value")
    private Integer nextValue;
    @Column(name = "next_lvl_cost")
    private Integer nextLvlCost;
}
