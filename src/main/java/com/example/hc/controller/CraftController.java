package com.example.hc.controller;

import com.example.hc.domain.model.Item;
import com.example.hc.repository.CraftItemForcesMappingRepository;
import com.example.hc.repository.ItemRepository;
import com.example.hc.service.CraftService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vyacheslav Savinov
 * @since 20.09.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/craft")
public class CraftController {

    private final CraftService craftService;
    private final ItemRepository itemRepository;
    private final CraftItemForcesMappingRepository craftItemForcesMappingRepository;

    @GetMapping("/chars/{charId}/items/{itemId}/options/{optionId}/forces/{forceId}")
    public void addOption(@PathVariable Integer charId, @PathVariable Integer itemId,
                          @PathVariable Long optionId, @PathVariable Integer forceId) {
        craftService.addOption(charId, itemId, optionId, forceId);
    }

    @GetMapping("/test")
    public List<Item> items() {
        return itemRepository.getInventoryByCharId(60);
    }
}