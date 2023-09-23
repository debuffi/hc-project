package com.example.hc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * @author Vyacheslav Savinov
 * @since 21.09.2023
 */
@Data
@Entity
@ToString
@Table(name = "craft_insert_property")
public class CraftInsertProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer charId;
    private Integer forceId;
    private Integer itemId;
    private Long opt;
    private String optBinary;
    private Long newOption;
    private String newOptionBinary;
    private String serialBinary;
    private boolean handled;
}
