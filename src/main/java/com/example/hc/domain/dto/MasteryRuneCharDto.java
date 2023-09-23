package com.example.hc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasteryRuneCharDto {
    private String name;
    private Integer forceId;
    private Integer type;
    private Integer value;
    private Integer nextValue;
    private Integer maxLvl;
    private Integer currentLvl;
    private Integer nextLvlCost;
}
