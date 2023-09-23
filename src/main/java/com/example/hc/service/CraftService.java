package com.example.hc.service;

import com.example.hc.domain.entity.CraftInsertProperty;
import com.example.hc.domain.model.Item;
import com.example.hc.repository.CraftInsertPropertyRepository;
import com.example.hc.repository.CraftItemForcesMappingRepository;
import com.example.hc.repository.ItemRepository;
import com.example.hc.service.util.ItemUtil;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Vyacheslav Savinov
 * @since 20.09.2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CraftService {

    private final ItemRepository itemRepository;
    private final CraftInsertPropertyRepository craftInsertPropertyRepository;
    private final CraftItemForcesMappingRepository craftItemForcesMappingRepository;

    public void addOption(final Integer charId, final Integer itemId, final Long option, final Integer forceId) {
        Item item = new Item();
        final List<Item> items = itemRepository.getInventoryByCharId(charId);
        for (final Item item1 : items) {
            if (item1.getItemId().equals(itemId) && item1.getOption().equals(option)) {
                item = item1;
                break;
            }
        }

        CraftInsertProperty property = new CraftInsertProperty();
        property.setCharId(charId);
        property.setOpt(option);
        property.setForceId(forceId);
        property.setItemId(itemId);
        property.setHandled(false);
        property.setNewOption(getNewOption(itemId, option, forceId));
        property.setNewOptionBinary(ItemUtil.toBinaryReversed(property.getNewOption(), 8));
        property.setOptBinary(getOptionBinary(option, forceId, item.getItemId() != null));

        if (item.getItemId() != null) {
            property.setSerialBinary(item.getSerialString());
        }

        craftInsertPropertyRepository.save(property);
        log.info("TEST, property: {}", property);
    }

    private String getOptionBinary(final Long option, final Integer forceId, boolean hasItem) {
        if(forceId == 0 && !hasItem){
            String binary = ItemUtil.toBinary(option, 8);
            final char[] chars = binary.toCharArray();
            chars[4] = '2';
            return new String(chars);
        }
        return ItemUtil.toBinary(option, 8);
    }

    private Long getNewOption(Integer itemId, Long option, final Integer forceId) {
        char[] options = ItemUtil.toBinary(option, 8).toCharArray();
        Integer level;
        char result;
        if(forceId == 0 && options[4] == '1') {
            boolean success = new Random().nextInt(100) < 20;
            if(!success){
                options[6] = '2';
                return Long.parseLong(new String(options), 16);
            } else {
                result = options[7];
            }
        } else {
            level = craftItemForcesMappingRepository.findLevelByItemIdAndForceId(itemId, forceId);
            result = String.format("%01X", level).toCharArray()[0];
        }

        if (result == options[7]) {
            char tmp = options[7];
            options[7] = options[5];
            options[5] = tmp;
            options[4] = '2';
        } else {
            options[6] = '2';
        }

        return Long.parseLong(new String(options), 16);
    }

}
