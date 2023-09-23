package com.example.hc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 22.09.2023
 */
@Data
@Entity
@Table(name = "craft_item_forces_mapping")
public class CraftItemForcesMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itemType;
    private Integer level;
    private Integer forceId;
}