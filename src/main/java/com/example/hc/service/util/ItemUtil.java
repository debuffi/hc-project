package com.example.hc.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hc.domain.model.Item;
import static com.example.hc.constants.ItemMsg.ITEMS;

import lombok.experimental.UtilityClass;

/**
 * @author Vyacheslav Savinov
 * @since 07.02.2023
 */
@UtilityClass
public class ItemUtil {

    public static Item toModel(String binaryItem) {
        return toModel(new String[]{binaryItem}).get(0);
    }

    public static List<Item> toGuildModel(String[] itemAsArray) {
        if (itemAsArray.length == 1 && itemAsArray[0].length() == 2) return new ArrayList<>();
        List<Item> result = new ArrayList<>();
        for (String skill : itemAsArray) {
            if (skill.length() == 0) continue;
            String first = skill.substring(0, 4);
            String kindIdAsString = skill.substring(4, 12);
            String otherBinaryData = skill.substring(12);

            String[] itemArray = kindIdAsString.split("(?<=\\G.{2})");
            int kindId = Integer.parseInt(itemArray[3] + itemArray[2] + itemArray[1] + itemArray[0], 16);
            int itemId = kindId & 4095;
            String itemMsg = ITEMS.stream().filter(s -> s.contains("\"item" + itemId + "\"")).findFirst().get();
            String itemName = itemMsg.substring(itemMsg.indexOf("cont=\"") + 6, itemMsg.lastIndexOf("\""));
            result.add(new Item(itemId, kindId, otherBinaryData, itemName, first, null, null, null, null));
        }
        return result;
    }

    public String toGuildBinary(List<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();
        items.forEach(item -> {
            String kindId = toBinary(item.getKindId(), 8);
            String binaryResult = item.getFirstBinaryData() + kindId + item.getOtherBinaryData();
            stringBuilder.append(binaryResult);
        });
        return stringBuilder.toString();
    }

    public static List<Item> toModel(String[] itemAsArray) {
        List<Item> result = new ArrayList<>();
        for (String skill : itemAsArray) {
            String itemIdSerialOption = skill.substring(0, 24);
            String serialString = skill.substring(8, 16);
            String optionString = skill.substring(16, 24);
            String kindIdAsString = skill.substring(0, 8);
            String otherBinaryData = skill.substring(8);
            String[] itemArray = kindIdAsString.split("(?<=\\G.{2})");
            String[] optionStringArr = optionString.split("(?<=\\G.{2})");
            String[] serialStringArray = serialString.split("(?<=\\G.{2})");
            int kindId = Integer.parseInt(itemArray[3] + itemArray[2] + itemArray[1] + itemArray[0], 16);
            Long serial = Long.parseLong(serialStringArray[3] + serialStringArray[2] + serialStringArray[1] + serialStringArray[0], 16);
            Long option = Long.parseLong(optionStringArr[3] + optionStringArr[2] + optionStringArr[1] + optionStringArr[0], 16);
            int itemId = kindId & 4095;
            String itemMsg = ITEMS.stream().filter(s -> s.contains("\"item" + itemId + "\"")).findFirst().orElse("");
            String itemName = itemMsg.equals("") ? "" : itemMsg.substring(itemMsg.indexOf("cont=\"") + 6, itemMsg.lastIndexOf("\""));
            result.add(new Item(itemId, kindId, otherBinaryData, itemName, null, itemIdSerialOption, serial, option, serialString));
        }
        return result;
    }

    public String toBinary(Item item) {
        return toBinary(List.of(item));
    }

    public String toBinary(List<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();
        items.forEach(item -> {
            String kindId = toBinary(item.getKindId(), 8);
            String binaryResult = kindId + item.getOtherBinaryData();
            stringBuilder.append(binaryResult);
        });
        return stringBuilder.toString();
    }

    public String toBinary(Number var, Integer countBytes) {
        return Arrays.stream(String.format("%0" + countBytes + "X", var).split("(?<=\\G.{2})")).collect(ArrayList::new,
                (list, e) -> list.add(0, e),
                (list1, list2) -> list1.addAll(0, list2)).stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    public String toBinaryReversed(Number var, Integer countBytes) {
        return Arrays.stream(String.format("%0" + countBytes + "X", var).split("(?<=\\G.{2})")).sorted((a, b) -> -1).collect(ArrayList::new,
            (list, e) -> list.add(0, e),
            (list1, list2) -> list1.addAll(0, list2)).stream().map(String::valueOf).collect(Collectors.joining(""));
    }



    public static void setNewItemId(Item item, Integer value) {
        item.setKindId(item.getKindId() - item.getItemId() + value);
        item.setItemId(value);
    }
}
