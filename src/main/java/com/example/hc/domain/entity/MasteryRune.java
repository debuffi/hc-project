package com.example.hc.domain.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@Data
@Entity
@Table(name = "mastery_rune")
public class MasteryRune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "force_id")
    private Integer forceId;
    private Integer type;
    @Column(name = "max_lvl")
    private Integer maxLvl;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MasteryRuneConfig> masteryRuneConfigs;
}
