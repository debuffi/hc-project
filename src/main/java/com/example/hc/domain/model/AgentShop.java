package com.example.hc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 03.04.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentShop {
    private Item item;
    private Integer userNum;
    private Integer slotId;
}
