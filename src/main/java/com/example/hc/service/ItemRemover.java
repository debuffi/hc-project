package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.hc.constants.ItemConstants;
import com.example.hc.domain.model.Account;
import com.example.hc.domain.model.Character;
import com.example.hc.domain.model.Item;
import com.example.hc.repository.ItemRepository;
import com.example.hc.service.util.ItemUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 11.04.2023
 */
@Service
@RequiredArgsConstructor
public class ItemRemover {

    private final CharacterService characterService;
    private final ItemRepository itemRepository;

    public void remove(List<Item> list) {
        Set<Integer> items = ItemConstants.TO_DELETE_LOW;
        list.removeIf(item -> items.contains(item.getItemId()));
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

    public List<Item> patchAcc(Integer usernum) {
        List<Item> result = new ArrayList<>();
        System.out.println("========= START " + usernum + "===============");

        List<Item> warehouse = itemRepository.getWarehouseByUserNum(usernum);
        List<Item> warehouseExt = itemRepository.getWarehouseExtByUserNum(usernum);

        remove(warehouse);
        remove(warehouseExt);

        String binaryWarehouseAsString = ItemUtil.toBinary(warehouse);
        String binaryWarehouseExtAsString = ItemUtil.toBinary(warehouseExt);
        itemRepository.updateWarehouseByUserNum(usernum, binaryWarehouseAsString);
        itemRepository.updateWarehouseExtByUserNum(usernum, binaryWarehouseExtAsString);

        result.addAll(warehouse);
        result.addAll(warehouseExt);
        System.out.println("========= END " + usernum + "===============");
        return result;
    }

    public List<Item> patchChar(Integer charId) {
        List<Item> result = new ArrayList<>();
        System.out.println("========= START " + charId + "===============");

        List<Item> equip = itemRepository.getEquipByCharId(charId);
        List<Item> inventory = itemRepository.getInventoryByCharId(charId);

        remove(equip);
        remove(inventory);

        String binaryEquipAsString = ItemUtil.toBinary(equip);
        String binaryInventoryAsString = ItemUtil.toBinary(inventory);
        itemRepository.updateEquipByCharId(charId, binaryEquipAsString);
        itemRepository.updateInventoryByCharId(charId, binaryInventoryAsString);

        result.addAll(equip);
        result.addAll(inventory);
        System.out.println("========= END " + charId + "===============");
        return result;
    }
}
