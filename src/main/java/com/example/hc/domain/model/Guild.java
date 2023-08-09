package com.example.hc.domain.model;

import java.util.List;

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
public class Guild {
    private Long id;
    private List<Item> items;
}
