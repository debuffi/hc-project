package com.example.hc.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.hc.domain.model.Item;
import com.example.hc.service.util.ItemUtil;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 07.02.2023
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final JdbcTemplate intJdbcTemplate;

    public List<Item> getEquipByCharId(Integer charId) {
        try {
            byte[] skillBytes = intJdbcTemplate.queryForObject(
                    "SELECT Data FROM Server01.dbo.cabal_equipment_table WHERE CharacterIdx=" + charId,
                    (rs, rowNum) -> rs.getBytes(1));
            if (skillBytes.length == 0) return new ArrayList<>();
            String binaryAsString = Hex.encodeHexString(skillBytes);
            return ItemUtil.toModel(binaryAsString.split("(?<=\\G.{36})"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Item> getWarehouseByUserNum(Integer userNum) {
        try {
            byte[] skillBytes = intJdbcTemplate.queryForObject(
                    "SELECT Data FROM Server01.dbo.cabal_warehouse_table WHERE UserNum=" + userNum,
                    (rs, rowNum) -> rs.getBytes(1));
            if (skillBytes.length == 0) return new ArrayList<>();
            String binaryAsString = Hex.encodeHexString(skillBytes);
            return ItemUtil.toModel(binaryAsString.split("(?<=\\G.{36})"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Item> getWarehouseExtByUserNum(Integer userNum) {

        try {
            byte[] skillBytes = intJdbcTemplate.queryForObject(
                    "SELECT Data FROM Server01.dbo.cabal_warehouse_ext_table WHERE UserNum=" + userNum,
                    (rs, rowNum) -> rs.getBytes(1));
            if (skillBytes.length == 0) return new ArrayList<>();
            String binaryAsString = Hex.encodeHexString(skillBytes);
            return ItemUtil.toModel(binaryAsString.split("(?<=\\G.{36})"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Item> getInventoryByCharId(Integer charId) {
        try {
            byte[] skillBytes = intJdbcTemplate.queryForObject(
                    "SELECT Data FROM Server01.dbo.cabal_Inventory_table WHERE CharacterIdx=" + charId,
                    (rs, rowNum) -> rs.getBytes(1));
            if (skillBytes.length == 0) return new ArrayList<>();
            String binaryAsString = Hex.encodeHexString(skillBytes);
            return ItemUtil.toModel(binaryAsString.split("(?<=\\G.{36})"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @SneakyThrows
    public void updateEquipByCharId(Integer charId, String binary) {
        if (Strings.isBlank(binary)) return;
        byte[] bytes = Hex.decodeHex(binary);
        intJdbcTemplate.update("UPDATE Server01.dbo.cabal_equipment_table SET Data = ? WHERE CharacterIdx=" + charId, bytes);
    }

    @SneakyThrows
    public void updateWarehouseByUserNum(Integer userNum, String binary) {
        if (Strings.isBlank(binary)) return;
        byte[] bytes = Hex.decodeHex(binary);
        intJdbcTemplate.update("UPDATE Server01.dbo.cabal_warehouse_table SET Data = ? WHERE UserNum=" + userNum, bytes);
    }

    @SneakyThrows
    public void updateWarehouseExtByUserNum(Integer userNum, String binary) {
        if (Strings.isBlank(binary)) return;
        byte[] bytes = Hex.decodeHex(binary);
        intJdbcTemplate.update("UPDATE Server01.dbo.cabal_warehouse_ext_table SET Data = ? WHERE UserNum=" + userNum, bytes);
    }

    @SneakyThrows
    public void updateInventoryByCharId(Integer charId, String binary) {
        if (Strings.isBlank(binary)) return;
        byte[] bytes = Hex.decodeHex(binary);
        intJdbcTemplate.update("UPDATE Server01.dbo.cabal_Inventory_table SET Data = ? WHERE CharacterIdx=" + charId, bytes);
    }

    public List<Item> getEquipByCharId2(Integer charId) {
        try {
            byte[] skillBytes = intJdbcTemplate.queryForObject(
                    "SELECT Data FROM Server01.dbo.cabal_equipment_table WHERE CharacterIdx=" + charId,
                    (rs, rowNum) -> rs.getBytes(1));
            if (skillBytes.length == 0) return new ArrayList<>();
            String binaryAsString = Hex.encodeHexString(skillBytes);
            return ItemUtil.toModel(binaryAsString.split("(?<=\\G.{36})"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
