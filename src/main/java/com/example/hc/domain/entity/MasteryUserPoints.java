package com.example.hc.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 10.08.2023
 */
@Data
@Entity
@Table(name = "mastery_user_points")
public class MasteryUserPoints {
    @Id
    @Column(name = "char_id")
    private Long charId;
    private Long points;
    private Long levelPoints;
}
