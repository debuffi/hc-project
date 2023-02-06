package com.example.hc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 02.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    private String name;
    private Integer charIdx;
    private Long style;
    private Integer classId;
}
