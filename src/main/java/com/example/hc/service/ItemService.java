package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.hc.domain.model.Account;
import com.example.hc.domain.model.Character;
import com.example.hc.domain.model.Item;
import com.example.hc.repository.ItemRepository;
import com.example.hc.service.util.ItemUtil;
import static com.example.hc.constants.ItemConstants.ITEM_MAPPING;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final CharacterService characterService;
    private final ItemRepository itemRepository;

    public List<Item> patchAcc(Integer usernum) {
        List<Item> result = new ArrayList<>();
        System.out.println("========= START " + usernum + "===============");

        List<Item> warehouse = itemRepository.getWarehouseByUserNum(usernum);
        List<Item> warehouseExt = itemRepository.getWarehouseExtByUserNum(usernum);

        List<Item> updatedWarehouse = processItems(warehouse);
        List<Item> updatedWarehouseExt = processItems(warehouseExt);

        String binaryWarehouseAsString = ItemUtil.toBinary(updatedWarehouse);
        String binaryWarehouseExtAsString = ItemUtil.toBinary(updatedWarehouseExt);
        itemRepository.updateWarehouseByUserNum(usernum, binaryWarehouseAsString);
        itemRepository.updateWarehouseExtByUserNum(usernum, binaryWarehouseExtAsString);

        result.addAll(updatedWarehouse);
        result.addAll(updatedWarehouseExt);
        System.out.println("========= END " + usernum + "===============");
        return result;
    }

    public List<Item> patchChar(Integer charId) {
        List<Item> result = new ArrayList<>();
        System.out.println("========= START " + charId + "===============");

        //List<Item> equip = itemRepository.getEquipByCharId(charId);
        List<Item> inventory = itemRepository.getInventoryByCharId(charId);

        //List<Item> updatedEquip = processItems(equip);
        List<Item> updatedInventory = processItems(inventory);

        //String binaryEquipAsString = ItemUtil.toBinary(updatedEquip);
        String binaryInventoryAsString = ItemUtil.toBinary(updatedInventory);
        //itemRepository.updateEquipByCharId(charId, binaryEquipAsString);
        itemRepository.updateInventoryByCharId(charId, binaryInventoryAsString);

        //result.addAll(updatedEquip);
        result.addAll(updatedInventory);
        System.out.println("========= END " + charId + "===============");
        return result;
    }

    private List<Item> processItems(List<Item> items) {
        List<Item> result = new ArrayList<>(items);
        //result.removeIf(x -> TO_DELETE.contains(x.getItemId()));
        for (Item item : result) {
            for (Map.Entry<Integer, Integer> itemMap : ITEM_MAPPING.entrySet()) {
                if (itemMap.getKey().equals(item.getItemId())) {
                    ItemUtil.setNewItemId(item, itemMap.getValue());
                    break;
                }
            }
        }

        return result;
    }


    public void patchChars() {
        List<Character> characters = characterService.getCharacters();
        for (Character character : characters) {
            patchChar(character.getCharIdx());
        }
    }

    public void patchAccs() {
        List<Account> accounts = characterService.getAccounts();
        for (Account account : accounts) {
            patchAcc(account.getUserNum());
        }
    }

    public void patchRingsChars() {
        List<Character> characters = characterService.getCharacters();
        for (Character character : characters) {
            Integer charId = character.getCharIdx();
            System.out.println("========= START " + charId + "===============");

            List<Item> equip = itemRepository.getEquipByCharId2(charId);
            List<Item> inventory = itemRepository.getInventoryByCharId(charId);

            List<Item> updatedEquip = processRingsItems(equip);
            List<Item> updatedInventory = processRingsItems(inventory);

            String binaryEquipAsString = ItemUtil.toBinary(updatedEquip);
            String binaryInventoryAsString = ItemUtil.toBinary(updatedInventory);
            itemRepository.updateEquipByCharId(charId, binaryEquipAsString);
            itemRepository.updateInventoryByCharId(charId, binaryInventoryAsString);

            System.out.println("========= END " + charId + "===============");
        }
    }

    private List<Item> processRingsItems(List<Item> items) {
        List<Item> result = new ArrayList<>(items);
        for (Item item : items) {
            Integer itemId = item.getItemId();
            if (itemId == 3662) { //темпус
                item.setOtherBinaryData(item.getOtherBinaryData().replace("d2d2a8", "d1d2a8").replace("d1d1a8", "d1d2a8"));
            }
            if (itemId == 3743) { //магел
                item.setOtherBinaryData(item.getOtherBinaryData().replace("e2e2d8", "e1e2d8").replace("e1e1d8", "e1e2d8"));
            }
            if (itemId == 1356) { //совершенства
                item.setOtherBinaryData(item.getOtherBinaryData().replace("98d2d2", "98d2d1").replace("98d1d1", "98d2d1"));
            }
            if (itemId == 2568) { //деспота
                item.setOtherBinaryData(item.getOtherBinaryData().replace("98d2d2", "98d2d1").replace("98d1d1", "98d2d1"));
            }
            if (itemId == 2203) { //мергахефа
                item.setOtherBinaryData(item.getOtherBinaryData().replace("989191", "989291").replace("989292", "989291"));
            }

        }
        return result;
    }
}

