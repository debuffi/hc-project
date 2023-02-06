package com.example.hc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private Integer id;
    private String name;
    private Integer lvl;
    private Integer slotId;
}
