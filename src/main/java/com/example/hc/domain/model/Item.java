package com.example.hc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 07.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer itemId;
    private Integer kindId;
    private String otherBinaryData;
    private String name;
    private String firstBinaryData;

    public Item(Integer itemId, Integer kindId) {
        this.itemId = itemId;
        this.kindId = kindId;
    }
}
