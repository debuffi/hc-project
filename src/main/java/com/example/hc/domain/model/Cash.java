package com.example.hc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.04.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cash {
    private Long id;
    private Item item;
}
